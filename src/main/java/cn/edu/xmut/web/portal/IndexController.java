package cn.edu.xmut.web.portal;

import javax.servlet.http.HttpServletRequest;

import cn.edu.xmut.core.utils.StringUtils;
import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import cn.edu.xmut.modules.bdinfo.service.BdInfoService;
import cn.edu.xmut.utils.JsonTool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("controller")
@RequestMapping("")
public class IndexController extends SummerBaseController{
	private final String INDEX = "";

    @Autowired
    private BdInfoService bdInfoService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return INDEX + "login";
	}

    @RequestMapping("/search")
    public String search(HttpServletRequest request) {
        return INDEX + "search";
    }

    @RequestMapping("/do-search")
    public @ResponseBody JSONObject doSearch(@RequestParam String insuredUserName, @RequestParam String bdNo) {
        if (StringUtils.isNotBlank(insuredUserName) && StringUtils.isNotBlank(bdNo)) {
            BdInfo bdInfo = bdInfoService.getByBdNoAndInsuredUserName(bdNo, insuredUserName);
            JSONObject json = new JSONObject();
            json.put("bdInfo", bdInfo);
            return json;
        } else {
            return JsonTool.genErrorMsg("请输入有效被保险人姓名和保单号");
        }
    }
}

