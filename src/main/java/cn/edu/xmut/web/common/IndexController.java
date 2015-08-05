package cn.edu.xmut.web.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.web.SummerBaseController;

@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("commonIndexController")
@RequestMapping(value = "/index")
public class IndexController  extends SummerBaseController{
	private final String INDEX = "/common/";
	
	@RequestMapping("")
	public String execute(HttpServletRequest request){
		return INDEX+getPath(request,true);
	}
}
