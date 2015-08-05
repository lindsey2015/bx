package cn.edu.xmut.web.userinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.userinfo.bean.UserInfo;
import cn.edu.xmut.modules.userinfo.service.UserInfoService;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;
/**
 * 
 * @描述 用户信息Controller
 * @作者 bob(傅文城)
 * @创建时间 2015年4月22日 上午10:19:36
 */
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("userInfoController")
@RequestMapping("userinfo")
public class UserInfoController extends BaseController{
	
	@Resource(name = "userInfoServiceImpl")
	public UserInfoService userInfoService;
	/**
	 * 
	 * @描述 保存用户信息
	 * @url  userinfo/save.jhtml
	 * @请求参数
	 * String userId 用户ID
	 * String name 投保人姓名
	 * String identity 身份证
	 * String birthday 出生日期
	 * boolean sex 性别
	 * String telephone 手机号码
	 * String email 电子邮箱
	 * String eContact 紧急联系人姓名
	 * String eTelephone 紧急联系人电话
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月22日 上午10:19:57
	 */
	@RequestMapping("/save")
	public @ResponseBody JSONObject save(UserInfo userInfo){
		if(!beanValidator(userInfo)){
			return JsonTool.genErrorMsg("验证失败");
		}else{
			
			UserInfo userInfo1 = userInfoService.getByOneField(UserInfo.FieldOfUserInfo.USER_ID.name(),userInfo.getUserId());
			if(userInfo1==null){
				userInfoService.save(userInfo1);
				return JsonTool.genSuccessMsg("保存成功");
			}else{
				userInfo1.setName(userInfo.getName());
				userInfo1.setIdentity(userInfo.getIdentity());
				userInfo1.setBirthday(userInfo.getBirthday());
				userInfo1.setSex(userInfo.isSex());
				userInfo1.setTelephone(userInfo.getTelephone());
				userInfo1.setEmail(userInfo.getEmail());
				userInfo1.seteContact(userInfo.geteContact());
				userInfo1.seteTelephone(userInfo.geteTelephone());
				userInfoService.save(userInfo1);
				return JsonTool.genSuccessMsg("保存成功");
			}
		}
	}
	
	/**
	 * 
	 * @描述 通过用户ID显示用户信息
	 * @url  userinfo/getByUserId.jhtml
	 * @请求参数
	 * String userId 用户ID
	 * @作者  bob(傅文城)
	 * @创建时间 2015年2月3日 上午10:22:53
	 */

	@RequestMapping("/getByUserId")
	public @ResponseBody JSONObject getByUserId(String userId) {
		UserInfo userInfo = userInfoService.getByOneField(UserInfo.FieldOfUserInfo.USER_ID.name(),userId);
		JSONObject json = new JSONObject();
		if (userInfo != null) {
			json.put("data", userInfo);
		} else {
			  UserInfo userInfo1 = new UserInfo();
			    userInfo1.setUserId(userId);
			    userInfo1.setName("0");
				userInfo1.setIdentity("0");
				userInfo1.setBirthday("2015-04-27");
				userInfo1.setSex(true);
				userInfo1.setTelephone("0");
				userInfo1.setEmail("0");
				userInfo1.seteTelephone("0");
				userInfo1.seteContact("0");
				userInfoService.save(userInfo1);
			json.put("data", userInfo1);
		}

		return json;
	}
	
	/**
	 * 
	 * @描述 通过用户ID显示用户信息
	 * @url  userinfo/get.jhtml
	 * @请求参数
	 * @作者  bob(傅文城)
	 * @创建时间 2015年2月3日 上午10:22:53
	 */

	@RequestMapping("/get")
	public @ResponseBody JSONObject get() {
		User user = (User) UtilCtrl.currentUser(User.class);
		UserInfo userInfo = userInfoService.getByOneField(UserInfo.FieldOfUserInfo.USER_ID.name(),user.getId());
		JSONObject json = new JSONObject();
		if (userInfo != null) {
			json.put("data", userInfo);
		} else {
			  UserInfo userInfo1 = new UserInfo();
			    userInfo1.setUserId(user.getId());
			    userInfo1.setName("0");
				userInfo1.setIdentity("0");
				userInfo1.setBirthday("2015-04-27");
				userInfo1.setSex(true);
				userInfo1.setTelephone("0");
				userInfo1.setEmail("0");
				userInfo1.seteTelephone("0");
				userInfo1.seteContact("0");
				userInfoService.save(userInfo1);
			json.put("data", userInfo1);
		}

		return json;
	}

}
