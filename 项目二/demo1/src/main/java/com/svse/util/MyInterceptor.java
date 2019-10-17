package com.svse.util;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

		HttpSession session = arg0.getSession();
		Object obj = null;
		obj = session.getAttribute("user");

		 if (obj != null) {
			
			return true;
		} else {
			arg1.sendRedirect("/user.do?method=index1&begin=0");
			return false;
		}
	}

}
