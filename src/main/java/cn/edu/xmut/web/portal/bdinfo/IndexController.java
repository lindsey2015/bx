package cn.edu.xmut.web.portal.bdinfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.web.SummerBaseController;
/**
 * 
 * @描述 保单IndexController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月22日 上午10:31:54
 */
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("bdInfoIndexController")
@RequestMapping("common/bdinfo")
public class IndexController extends SummerBaseController{
	private final String INDEX = "/common/bdinfo/";
	@RequestMapping("")
	public String login(HttpServletRequest request){
		return INDEX+getPath(request);
	}		
}