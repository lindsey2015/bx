package cn.edu.xmut.web.portal.toubao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.web.SummerBaseController;
/**
 * 
 * @描述 投保IndexController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月22日 上午10:31:54
 */
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("toubaoIndexController")
@RequestMapping("common/toubao")
public class IndexController extends SummerBaseController{
	private final String INDEX = "/common/toubao/";
	@RequestMapping("")
	public String login(HttpServletRequest request){
		return INDEX+getPath(request);
	}		
}