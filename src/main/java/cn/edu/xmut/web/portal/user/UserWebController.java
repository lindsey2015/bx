/**
 * 
 */
package cn.edu.xmut.web.portal.user;

import javax.annotation.Resource;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.UtilCtrl;
import cn.edu.xmut.web.user.UserController;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @描述 用户WebController
 * @作者 bob(傅文城)
 * @创建时间 2015年3月5日 下午1:57:46
 */
@Controller("pUserWeb")
@RequestMapping(value = "pUserWeb")
public class UserWebController {

	@Resource(name = "userController")
	private UserController userController;
	
	
	public ModelMap pageByTypeSearchParam() {
		Pageable pageable = PageUtils.getPageable();
		int type = UtilCtrl.getRequestIntValueDefaultZero("type");
		String searchParam = UtilCtrl.getRequestValueNotNull("searchParam");
		ModelMap model = new ModelMap();		
		JSONObject result = userController.pageByTypeSearchParam(pageable,searchParam,type);
	    model.put("data", result.getJSONObject("data"));
		return model;
	}
	

}
