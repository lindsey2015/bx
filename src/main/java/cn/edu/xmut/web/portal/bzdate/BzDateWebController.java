package cn.edu.xmut.web.portal.bzdate;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.utils.UtilCtrl;
import cn.edu.xmut.web.bzdate.BzDateController;
/**
 * 
 * @描述 保障日期WebController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月22日 上午10:30:45
 */
@Controller("pBzDateWeb")
@RequestMapping(value = "pBzDateWeb")
public class BzDateWebController {
	
         @Resource(name = "bzDateController")
         public BzDateController bzDateController;
         
         
         public ModelMap listByAgeGroupId() {
 			ModelMap model = new ModelMap();	
 			String ageGroupId = UtilCtrl.getRequestValueNotNull("ageGroupId");
 			JSONObject result = bzDateController.listByAgeGroupId(ageGroupId);
 			model.put("data",result.get("data"));
 			return model;
 		}
}
