package cn.edu.xmut.web.bdinfo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmut.core.utils.ExcelUtils;

import cn.edu.xmut.core.utils.MailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.core.utils.DateUtils;
import cn.edu.xmut.core.utils.StringUtils;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import cn.edu.xmut.modules.bdinfo.service.BdInfoService;
import cn.edu.xmut.modules.bzdate.service.BzDateService;
import cn.edu.xmut.modules.catagory.service.CatagoryService;
import cn.edu.xmut.modules.product.service.ProductService;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.userproduct.bean.UserProduct;
import cn.edu.xmut.modules.userproduct.service.UserProductService;
import cn.edu.xmut.utils.DateTool;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

/**
 * @描述 保单信息Controller
 * @作者 bob(傅文城)
 * @创建时间 2015年4月23日 上午11:26:28
 */
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("bdInfoController")
@RequestMapping("bdinfo")
public class BdInfoController extends BaseController {
    @Autowired
    private BdInfoService bdInfoService;
    @Autowired
    private BzDateService bzDateService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserProductService userProductService;
    @Autowired
    private CatagoryService catagoryService;
    @Autowired
    private MailUtility mailUtility;

    @RequestMapping("/export")
    public
    @ResponseBody
    JSONObject export(String startTime, String endTime, int status) throws IOException {
        String targetFile = UtilCtrl.getSession().getServletContext().getRealPath("upload");
        String file = targetFile + "\\" + DateTool.getCurrDate() + ".xls";

        List<BdInfo> list = bdInfoService.listOrderBy(BdInfo.FieldOfBdInfo.START_DAY.name(), startTime,
                BdInfo.FieldOfBdInfo.START_DAY.name(), endTime,
                BdInfo.FieldOfBdInfo.STATUS.name(), status,
                BdInfo.FieldOfBdInfo.CREATE_TIME.name() + " DESC");

        ExcelUtils.exportToExcel(file, list);
        return JsonTool.genSuccessMsg("upload/" + DateTool.getCurrDate() + ".xls");
    }

    @RequestMapping("/checktotal")
    public
    @ResponseBody
    JSONObject checkTotal(int bzDate, String ageGroupId, int nums) {
        double value = bzDateService.getValue(bzDate, ageGroupId);
        double total = nums * value;
        return JsonTool.genSuccessMsg(total);
    }

    @RequestMapping("/add")
    public
    @ResponseBody
    JSONObject add(@RequestBody BdInfo bdInfo) {
        if (!beanValidator(bdInfo)) {
            return JsonTool.genErrorMsg("验证失败");
        } else {
            User user = (User) UtilCtrl.currentUser(User.class);
            bdInfo.setUser(user);
            bdInfo.setCreateTime(DateUtils.getDateTime());
            double actualTotalFee = bdInfoService.getActualTotalFee(bdInfo);
            bdInfo.setTotal(actualTotalFee);
            bdInfo.setNo(bdInfoService.getSequence());
            bdInfo.setTbNo(bdInfoService.generateTbNumber(bdInfo));
            if (bdInfo.getStatus() == 2) {
                approveBd(bdInfo);
            }
            bdInfoService.save(bdInfo);
            return JsonTool.genSuccessMsg("操作成功。实际保费 " + actualTotalFee + "元");
        }
    }

    private void approveBd(BdInfo bdInfo) {
        bdInfo.setStatus(3);
        bdInfo.setBdNo(bdInfoService.generateBdNumber(bdInfo));
        mailUtility.sendSuccessNotification(bdInfo);
    }

    @RequestMapping("/pageByStatusSearchParam")
    public
    @ResponseBody
    JSONObject pageByStatusSearchParam(Pageable pageable, int status, String searchParam, String startTime, String endTime) {
        Page<BdInfo> list = null;
        if (StringUtils.isNotBlank(searchParam)) {
            list = bdInfoService.findPageByTwoFieldsLike(pageable,
                    BdInfo.FieldOfBdInfo.STATUS.name(), status,
                    BdInfo.FieldOfBdInfo.TB_NO.name(), searchParam);
        } else if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            list = bdInfoService.findPageByTwoFields(pageable,
                    BdInfo.FieldOfBdInfo.STATUS.name(), status,
                    BdInfo.FieldOfBdInfo.START_DAY.name(), startTime,
                    BdInfo.FieldOfBdInfo.START_DAY.name(), endTime);
        } else {
            list = bdInfoService.findPageByOneFieldOrderBy(pageable,
                    BdInfo.FieldOfBdInfo.STATUS.name(), status,
                    BdInfo.FieldOfBdInfo.CREATE_TIME.name() + " desc");
        }
        JSONObject json = new JSONObject();
        json.put("data", list.getContent());
        JSONArray array = json.getJSONArray("data");

        List<BdInfo> parse = JSON.parseArray(array.toJSONString(), BdInfo.class);
        User user = (User) UtilCtrl.currentUser(User.class);
        json.put("type", user.getType());
        if (user.getType() == 3) {
            List<BdInfo> resultBdInfos = new ArrayList<BdInfo>();
            List<UserProduct> userProducts = userProductService.findByOneFieldOrderBy(UserProduct.FieldOfUserProduct.USER_ID.name(), user.getId(), " rand()");
            for (UserProduct userProduct : userProducts) {
                for (BdInfo bdinfo : parse) {
                    if (bdinfo.getProduct().getId().equals(userProduct.getProductId())) {
                        resultBdInfos.add(bdinfo);
                    }
                }
            }
            Page<BdInfo> dics = new Page<BdInfo>(resultBdInfos, resultBdInfos.size(), pageable);
            json.put("data", dics);
            return json;
        } else if (user.getType() == 2) {
            List<BdInfo> resultBdInfos = new ArrayList<BdInfo>();
            for (BdInfo bdInfo : parse) {
                if (user.getId().equals(bdInfo.getUser().getId())) {
                    resultBdInfos.add(bdInfo);
                }
            }
            Page<BdInfo> dics = new Page<BdInfo>(resultBdInfos, resultBdInfos.size(), pageable);
            json.put("data", dics);
            return json;
        } else {
            Page<BdInfo> dics = new Page<BdInfo>(parse, list.getTotal(), pageable);
            json.put("data", dics);
            return json;
        }
    }

    @RequestMapping("/getById")
    public
    @ResponseBody
    JSONObject getById(String id) {
        if (StringUtils.isEmpty(id)) {
            return JsonTool.genErrorMsg("ID为空");
        } else {
            BdInfo bdInfo = bdInfoService.findById(id);
            return JsonTool.genSuccessMsg(bdInfo);
        }
    }

    @RequestMapping("/check")
    public
    @ResponseBody
    JSONObject check(String bdNo, String id, String pdfAddr) {
        BdInfo bdInfo = bdInfoService.findById(id);
        bdInfo.setPdfAddr(pdfAddr);
        approveBd(bdInfo);
        bdInfoService.save(bdInfo);
        return JsonTool.genSuccessMsg("审核通过");
    }

    @RequestMapping("/updateStatus")
    public
    @ResponseBody
    JSONObject updateStatus(String id, String startDay) {
        if (startDay.compareTo(DateTool.getCurrDate()) > 0) {
            BdInfo bdInfo = bdInfoService.findById(id);
            approveBd(bdInfo);
            bdInfoService.save(bdInfo);
            mailUtility.sendSuccessNotification(bdInfo);
            return JsonTool.genSuccessMsg("审核通过");
        } else {
            return JsonTool.genErrorMsg("审核失败，起保日期必须大于当前时间");
        }

    }

    @RequestMapping("/delbdinfo")
    public
    @ResponseBody
    JSONObject delbdinfo(String id) {
        BdInfo bdInfo = bdInfoService.findById(id);
        if (bdInfo != null) {
            bdInfoService.deleteById(bdInfo.getId());
            return JsonTool.genSuccessMsg("删除成功");
        } else {
            return JsonTool.genErrorMsg("该保单不存在");
        }
    }
}
