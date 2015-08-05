package cn.edu.xmut.core.filter;


import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.edu.xmut.modules.user.bean.User;



public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static final Logger LOG = LoggerFactory
			.getLogger(LoginInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
	    // 获取注解上的类
	    Class<?> clazz = method.getDeclaringClass();
		Filter classFilter = clazz.getAnnotation(Filter.class);
		// 获取注解上的方法
		Filter methodFilter = method.getAnnotation(Filter.class);
		// 判断是否有拦截
		if(null == classFilter && null == methodFilter) {
			return true;
		}
		
		HttpSession session = request.getSession();
		
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		User user  = (User) session.getAttribute("user");
		
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getRequestURI();
		String query = request.getQueryString();
		String from =URLEncoder.encode(url+(query == null ? "" : "?" + query),"UTF-8");
		//String from =url+(query == null ? "" : "?" + query);
		if(url.endsWith("bx/") || url.endsWith("login.jhtml") || url.endsWith("bx")) {
			return true;
		}
		
		if(null == user) {
			response.sendRedirect(basePath+"login.jhtml?from="+from);
			LOG.warn("After logging in, to do the next operation,url:"+request.getServletPath());
			return false;
		}
		return true;
		
	}

	

}
