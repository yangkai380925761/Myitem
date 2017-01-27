package com.tgb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor  implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		String url = req.getRequestURI(); 
		if(url.indexOf("login.action")>=0){  
            return true;  
        }  
		if(url.indexOf("js")>=0){  
			return true;  
		}  
		if(url.indexOf("css")>=0){  
			return true;  
		}  
		if(url.indexOf("images")>=0){  
			return true;  
		}  
		
		String str =  (String) req.getSession().getAttribute("userNameNum");  
        System.out.println("str=========>"+str);  
        if(str!=null){  
            return true;  
        }
        //res.sendRedirect("Myitem/user/login");
        return false;
	}

}
