package cn.edu.xmut.web.bzdate;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.core.utils.StringUtils;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.bzdate.bean.BzDate;
import cn.edu.xmut.modules.bzdate.service.BzDateService;
import cn.edu.xmut.utils.JsonTool;
/**
 * 
 * @描述 保障日期Controller
 * @作者 bob(傅文城)
 * @创建时间 2015年4月20日 下午3:41:59
 */
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("bzDateController")
@RequestMapping("bzdate")
public class BzDateController extends BaseController{
	

    @Resource(name = "bzDateServiceImpl")
    public BzDateService bzDateService;
    
    /**
     * 
     * @描述 通过年龄段ID获取保障天数列表
     * @url  bzdate/listByAgeGroupId.jhtml
     * @请求参数
     * String ageGroupId 年龄段ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月20日 下午5:53:59
     */
    @RequestMapping("/listByAgeGroupId")
	public @ResponseBody JSONObject listByAgeGroupId(String ageGroupId) {		
    	List<BzDate> bzDates =null;
    	if(StringUtils.isEmpty(ageGroupId)){
			return JsonTool.genErrorMsg("年龄段ID为空");
		}else{
			bzDates = bzDateService.findByOneFieldOrderBy(BzDate.FieldOfBzDate.AGE_GROUP_ID.name(),ageGroupId,BzDate.FieldOfBzDate.MIN_DAY.name()+" asc");
		return JsonTool.genSuccessMsg(bzDates);
		}
	}
    /**
     * 
     * @描述 通过年龄段ID获取最小-最大天数列表
     * @url  bzdate/listAllByAgeGroupId.jhtml
     * @请求参数
     * String ageGroupId 年龄段ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月20日 下午5:53:59
     */
    @RequestMapping("/listAllByAgeGroupId")
	public @ResponseBody JSONObject listAllByAgeGroupId(String ageGroupId) {		
    	if(StringUtils.isEmpty(ageGroupId)){
			return JsonTool.genErrorMsg("年龄段ID为空");
		}else{
		int min = bzDateService.getMin(ageGroupId);
		int max = bzDateService.getMax(ageGroupId);
		List<BzDate> bzDates = new ArrayList<BzDate>();
		for(int i=min;i<=max;i++){
			BzDate bzDate = new BzDate();
			bzDate.put("days",i);
			bzDates.add(bzDate);
		}
		return JsonTool.genSuccessMsg(bzDates);
		}
	}
   
    /**
     * 
     * @描述 新增保障日期
     * @url  bzdate/add.jhtml
     * @请求参数
     * String ageGroupId 年龄段ID
	 *  String minDay 最小天数
	 *  String maxDay 最大天数
	 *  double value 费率
     * @作者  bob(傅文城)
     * @创建时间 2015年4月20日 下午3:44:19
     */
    @RequestMapping("/add")
    public @ResponseBody JSONObject add(BzDate bzDate){
   	 if(!beanValidator(bzDate)){
			return JsonTool.genErrorMsg("验证失败");
		}else{
			if(bzDate.getMaxDay()<bzDate.getMinDay()){
				return JsonTool.genErrorMsg("最大天数不能小于最小天数");
			}else{
		List<BzDate> isExist = bzDateService.check(bzDate.getMinDay(),bzDate.getMaxDay(),bzDate.getAgeGroupId());
		if(isExist.size()!=0){
			return JsonTool.genErrorMsg("天数区间重叠");
		}else{
			bzDateService.save(bzDate);
			return JsonTool.genSuccessMsg("保存成功");
			}
			}	
		}
    }
  
    /**
     * 
     * @描述 修改型号属性
     * @url  agegroup/update.jhtml
     * @请求参数
     * String id 保障日期ID
	 *  String minDay 最小天数
	 *  String maxDay 最大天数
	 *  double value 费率
     * @作者  bob(傅文城)
     * @创建时间 2015年4月20日 下午5:29:57
     */
    @RequestMapping("/update")
    public @ResponseBody JSONObject update(BzDate bzDate){
    	 if(!beanValidator(bzDate)){
 			return JsonTool.genErrorMsg("验证失败");
 		}else{
 			if(bzDate.getMaxDay()<bzDate.getMinDay()){
				return JsonTool.genErrorMsg("最大天数不能小于最小天数");
			}else{
				List<BzDate> isExist = bzDateService.check(bzDate.getMinDay(),bzDate.getMaxDay(),bzDate.getAgeGroupId());
				if(isExist.size()!=0){
					if(isExist.get(0).getId().equals(bzDate.getId())&&isExist.size()==1){
						BzDate bzDate1 = bzDateService.findById(bzDate.getId());
						bzDate1.setMinDay(bzDate.getMinDay());
						bzDate1.setMaxDay(bzDate.getMaxDay());
						bzDate1.setValue(bzDate.getValue());
						bzDateService.save(bzDate1);
						return JsonTool.genSuccessMsg("更新成功");
					}else{
						
						return JsonTool.genErrorMsg("价格区间重叠");
					}
				}else{
					BzDate bzDate1 = bzDateService.findById(bzDate.getId());
					bzDate1.setMinDay(bzDate.getMinDay());
					bzDate1.setMaxDay(bzDate.getMaxDay());
					bzDate1.setValue(bzDate.getValue());
					bzDateService.save(bzDate1);
					return JsonTool.genSuccessMsg("更新成功");
					}
				}
     	}
    }
    /**
     * 
     * @描述 批量删除年龄段
     * @url  bzdate/del.jhtml
     * @请求参数
     * String ids 修改的id数组
     * @作者  bob(傅文城)
     * @创建时间 2015年4月21日 下午1:37:39
     */
    @RequestMapping("/del")
	public @ResponseBody JSONObject del(String[] ids) {		
		if(ids.length>0){
			for (String id : ids) {	 
				bzDateService.deleteById(id);	
			}
		}
		return JsonTool.genSuccessMsg("删除成功");
	}
}
