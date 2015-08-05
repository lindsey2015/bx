package cn.edu.xmut.web.portal.agegroup;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.utils.UtilCtrl;
import cn.edu.xmut.web.agegroup.AgeGroupController;
/**
 * 
 * @描述 年龄段WebController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月22日 上午10:30:45
 */
@Controller("pAgeGroupWeb")
@RequestMapping(value = "pAgeGroupWeb")
public class AgeGroupWebController {
	
         @Resource(name = "ageGroupController")
         public AgeGroupController ageGroupController;
         
         
         public ModelMap listByProductId() {
 			ModelMap model = new ModelMap();	
 			String productId = UtilCtrl.getRequestValueNotNull("productId");
 			JSONObject result = ageGroupController.listByProductId(productId);
 			model.put("data",result.get("data"));
 			return model;
 		}
}
