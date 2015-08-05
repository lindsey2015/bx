package cn.edu.xmut.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.UserService;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;
@Controller("userloginController")
@RequestMapping("userlogin")
/**
 * 
* @ClassName: UserController
* @Description: 
* @author bob(傅文城)
* @date 2015年4月21日 上午10:38:49
*
 */
public class UserloginController extends BaseController {
	@Resource(name = "userServiceImpl")
	public UserService userService;

	@RequestMapping(value = "/login")
	public @ResponseBody JSONObject login(User user) {  
		User isExit = userService.getByTwoFields(User.FieldOfUser.USERNAME.name(), user.getUsername(), User.FieldOfUser.PASSWORD.name(), user.getPassword());
		
		if(isExit!=null) {
			if(isExit.isUseful()){
				UtilCtrl.sessionPut("user", isExit);
				return JsonTool.genSuccessMsg("登录成功");
			}else {
				return JsonTool.genErrorMsg("该用户已经被禁用，不能登录");
			}
		} else {
			return JsonTool.genErrorMsg("账号或者密码错误");
		}
	}
	@RequestMapping(value = "/logout")
	public @ResponseBody JSONObject logout() { 
		UtilCtrl.sessionRemove("user");
		return JsonTool.genSuccessMsg("注销成功");
	}
	
}
