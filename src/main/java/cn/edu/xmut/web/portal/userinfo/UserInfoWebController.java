/**
 * 
 */
package cn.edu.xmut.web.portal.userinfo;

import javax.annotation.Resource;

import cn.edu.xmut.utils.UtilCtrl;
import cn.edu.xmut.web.userinfo.UserInfoController;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @描述 用户信息WebController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月22日 下午1:57:46
 */
@Controller("pUserInfoWeb")
@RequestMapping(value = "pUserInfoWeb")
public class UserInfoWebController {

	@Resource(name = "userInfoController")
	private UserInfoController userInfoController;
	
	 public ModelMap getByUserId() {
	  		ModelMap model = new ModelMap();
	  		String userId = UtilCtrl.getRequestValueNotNull("userId");
	  		JSONObject result = userInfoController.getByUserId(userId);
	  		model.put("data", result.get("data"));
	  		return model;
	  	}
	 public ModelMap get() {
	  		ModelMap model = new ModelMap();
	  		JSONObject result = userInfoController.get();
	  		model.put("data", result.get("data"));
	  		return model;
	  	}

}
