package cn.edu.xmut.web.agegroup;

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
import cn.edu.xmut.modules.agegroup.bean.AgeGroup;
import cn.edu.xmut.modules.agegroup.service.AgeGroupService;
import cn.edu.xmut.modules.bzdate.bean.BzDate;
import cn.edu.xmut.modules.bzdate.service.BzDateService;
import cn.edu.xmut.utils.JsonTool;
/**
 * 
 * @描述 年龄段Controller
 * @作者 bob(傅文城)
 * @创建时间 2015年4月20日 下午3:41:59
 */
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("ageGroupController")
@RequestMapping("agegroup")
public class AgeGroupController extends BaseController{
	

    @Resource(name = "ageGroupServiceImpl")
    public AgeGroupService ageGroupService;
    @Resource(name = "bzDateServiceImpl")
    public BzDateService bzDateService;
    
    /**
     * 
     * @描述 通过产品ID获取年龄段列表
     * @url  agegroup/listByProductId.jhtml
     * @请求参数
     * String productId 所属产品ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月20日 下午5:53:59
     */
    @RequestMapping("/listByProductId")
	public @ResponseBody JSONObject listByProductId(String productId) {		
    	List<AgeGroup> ageGroups =null;
    	if(StringUtils.isEmpty(productId)){
			return JsonTool.genErrorMsg("产品ID为空");
		}else{
		ageGroups = ageGroupService.findByOneFieldOrderBy(AgeGroup.FieldOfAgeGroup.PRODUCT_ID.name(),productId,AgeGroup.FieldOfAgeGroup.ID.name()+" asc");
		for(AgeGroup ageGroup:ageGroups){
			int count = bzDateService.countByOneField(BzDate.FieldOfBzDate.AGE_GROUP_ID.name(),ageGroup.getId());
			ageGroup.put("count",count);
		}
		return JsonTool.genSuccessMsg(ageGroups);
		}
	}
   
    /**
     * 
     * @描述 新增年龄段
     * @url  agegroup/add.jhtml
     * @请求参数
     *  String productId 产品ID
	 *  String ageGroup 年龄段
     * @作者  bob(傅文城)
     * @创建时间 2015年4月20日 下午3:44:19
     */
    @RequestMapping("/add")
    public @ResponseBody JSONObject add(AgeGroup ageGroup){
   	 if(!beanValidator(ageGroup)){
			return JsonTool.genErrorMsg("验证失败");
		}else{
		  ageGroupService.save(ageGroup);
		return JsonTool.genSuccessMsg("保存成功");
		}
    }
  
    /**
     * 
     * @描述 修改型号属性
     * @url  agegroup/update.jhtml
     * @请求参数
    *  String productId 产品ID
	 *  String ageGroup 年龄段
     * @作者  bob(傅文城)
     * @创建时间 2015年4月20日 下午5:29:57
     */
    @RequestMapping("/update")
    public @ResponseBody JSONObject update(AgeGroup ageGroup){
    	 if(!beanValidator(ageGroup)){
 			return JsonTool.genErrorMsg("验证失败");
 		}else{
 			AgeGroup agegroup1 = ageGroupService.getByOneField(AgeGroup.FieldOfAgeGroup.ID.name(),ageGroup.getId());
 			agegroup1.setAgeGroup(ageGroup.getAgeGroup());
 			ageGroupService.save(agegroup1);
		return JsonTool.genSuccessMsg("更新成功");
     	}
    }
    /**
     * 
     * @描述 批量删除年龄段
     * @url  agegroup/del.jhtml
     * @请求参数
     * String ids 修改的id数组
     * @作者  bob(傅文城)
     * @创建时间 2015年4月21日 下午1:37:39
     */
    @RequestMapping("/del")
	public @ResponseBody JSONObject del(String[] ids) {		
		if(ids.length>0){
			for (String id : ids) {	 
				ageGroupService.deleteById(id);	
			}
		}
		return JsonTool.genSuccessMsg("删除成功");
	}
    
}
