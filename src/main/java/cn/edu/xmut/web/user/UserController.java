package cn.edu.xmut.web.user;

import java.util.List;

import javax.annotation.Resource;









import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;










import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.core.utils.StringUtils;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.UserService;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("userController")
@RequestMapping("user")
/**
 * 
 * @描述  用户管理controller
 * @作者  bob(傅文城)
 * @创建时间 2015年4月20日 下午4:29:19
 */
public class UserController extends BaseController {
	
	@Resource(name = "userServiceImpl")
	public UserService userService;
	
	/**
	 * 
	 * @描述  添加一个用户、保险公司
	 * @url  user/add.jhtml
	 * @请求参数
	 * String username 用户名
	 * int type 用户类型   1管理员 2用户 3保险公司
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月20日 下午4:29:19
	 */
	@RequestMapping("/add")
	public @ResponseBody JSONObject add(User user) {
		user.setUsername(user.getUsername().toLowerCase());
		if (!beanValidator(user)) {
			return JsonTool.genErrorMsg("验证失败");
		} else {
			User isExist = userService.getByOneField(
					User.FieldOfUser.USERNAME.name(), user.getUsername());
			if (isExist != null) {
				return JsonTool.genErrorMsg("用户名已存在");
			} else {
				user.setUseful(true);
			    userService.save(user);		
			    
				return JsonTool.genSuccessMsg("保存成功");
			}
		}
	}
	/**
	 * 
	 * @描述  用户修改密码
	 * @url  user/changePassword.jhtml
	 * @请求参数
	 * String password 密码
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月20日 下午4:29:19
	 */
	@RequestMapping("/changePassword")
	public @ResponseBody JSONObject changePassword(String password){
		if (StringUtils.isEmpty(password)) {
			return JsonTool.genErrorMsg("新密码不能为空");
		} else {
			User user = (User) UtilCtrl.currentUser(User.class);
			User user1 = userService.findById(user.getId());
			user1.setPassword(password);
			userService.save(user1);
			return JsonTool.genSuccessMsg("修改成功");
			
		}
	}
	/**
	 * 
	 * @描述  更新一个用户、保险公司
	 * @url  user/update.jhtml
	 * @请求参数
	 * String userId 用户ID
	 * String username 用户名
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月20日 下午4:29:19
	 */

	@RequestMapping("/update")
	public @ResponseBody JSONObject update(User user) {
		user.setUsername(user.getUsername().toLowerCase());
		if (!beanValidator(user)) {
			return JsonTool.genErrorMsg("验证失败");
		} else {
			User isExist = userService.findById(user.getId());
			if (isExist.getUsername().equals(user.getUsername())) {
				isExist.setPassword(user.getPassword());
				userService.save(isExist);
				return JsonTool.genSuccessMsg("更新成功(用户名没有修改)");
			} else {
				User isExistOther = userService.getByOneField(
						User.FieldOfUser.USERNAME.name(), user.getUsername());
				if (isExistOther != null) {
					return JsonTool.genErrorMsg("用户名已存在");
				} else {
					isExist.setUsername(user.getUsername());
					isExist.setPassword(user.getPassword());
					userService.save(isExist);
					return JsonTool.genSuccessMsg("更新成功");
				}
			}
		}
	}

	 /**
     * 
     * @描述 用户启用与禁用
     * @url  user/updateUseful.jhtml
     * @请求参数
     * int useful 是否启用
     * String ids 修改的id数组
     * @作者  bob(傅文城)
     * @创建时间 2015年4月21日 下午1:37:39
     */
    @RequestMapping("/updateUseful")
	public @ResponseBody JSONObject updateUseful(int useful, String[] ids) {		
		if(ids.length>0){
			for (String id : ids) {	 
				User user = userService.findById(id);
				if(user.isUseful()){
					if(1==useful){
						continue;
					}else{
						user.setUseful(false);
						userService.save(user);
					}
				}else {
					if(0==useful){
						continue;
					}else{
						user.setUseful(true);
						userService.save(user);
					}
				}			
			}
		}
		return JsonTool.genSuccessMsg("状态修改成功");
	}
    
	/**
	 * 
	 * @描述  通过TYPE，关键字分页
	 * @url  user/pageByTypeSearchParam.jhtml
	 * @请求参数
	 * Pageable pageable 分页信息
	 * String searchParam 关键字
	 * int type 用户类型
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月20日 下午4:29:19
	 */

	
	public @ResponseBody JSONObject pageByTypeSearchParam(Pageable pageable,String searchParam,int type) {		
		Page<User> list = null;
		if (searchParam != null && !"".equals(searchParam)) {			
			list = userService.findPageByTwoFieldsLikeOrderBy(pageable,User.FieldOfUser.TYPE.name(),type,User.FieldOfUser.USERNAME.name(),searchParam, User.FieldOfUser.USEFUL.name()+" DESC");
		} else {
			list = userService.findPageByOneFieldOrderBy(pageable,User.FieldOfUser.TYPE.name(),type,User.FieldOfUser.USEFUL.name()+" DESC");
		}
		JSONObject json = new JSONObject();
		json.put("data", list.getContent());
		JSONArray array = json.getJSONArray("data");			
		
		List<User> parse = JSON.parseArray(array.toJSONString(), User.class);
		Page<User> dics = new Page<User>(parse, list.getTotal(), pageable);
		json.put("data", dics);		
		return json;	
	}

}
