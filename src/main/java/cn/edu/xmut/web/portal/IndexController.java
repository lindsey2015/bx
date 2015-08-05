package cn.edu.xmut.web.portal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("controller")
@RequestMapping("")
public class IndexController extends SummerBaseController{
	private final String INDEX = "";
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		return INDEX+"login";
	}	
	

}

