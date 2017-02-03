package com.zh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginInterceptor implements HandlerInterceptor {
	
	private String toUrl;
	private String[] sessionAttr;
	
	
	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	

	public String[] getSessionAttr() {
		return sessionAttr;
	}

	public void setSessionAttr(String[] sessionAttr) {
		this.sessionAttr = sessionAttr;
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println("pre"+request.getServletPath());
/*		String servletPath = request.getServletPath();
		if(!(servletPath.contains("login")||servletPath.contains("css")||servletPath.contains("js")||servletPath.equals("/"))){
			System.out.println("interceptor");
			if(request.getSession().getAttribute(user) == null){
				String toUrl = request.getContextPath()+"/"+this.toUrl;
				System.out.println(toUrl);
				response.sendRedirect(toUrl);
				return false;
			}else return true;
		}else return true;*/
		boolean flag = false;
		for (String role : sessionAttr) {
			
			if(request.getSession().getAttribute(role) != null){
				flag = true;
			}
		}
		if(!flag){
			
			String toUrl = request.getContextPath()+"/"+this.toUrl;
			System.out.println("interceptor  "+toUrl);
			response.sendRedirect(toUrl);
		}
		return flag;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}




	
}
