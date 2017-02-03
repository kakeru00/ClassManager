package com.zh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class UpdateInterceptor implements HandlerInterceptor {
	
	private String toUrl;
	private String sessionAttr;
	private String sessionAttr2;
	
	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	

	public String getSessionAttr() {
		return sessionAttr;
	}

	public void setSessionAttr(String sessionAttr) {
		this.sessionAttr = sessionAttr;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println("pre"+request.getServletPath());

		
		if(request.getSession().getAttribute(sessionAttr) == null){
			if(request.getSession().getAttribute(sessionAttr2) == null){
				
				String toUrl = request.getContextPath()+"/"+this.toUrl;
				System.out.println("interceptor  "+toUrl);
				response.sendRedirect(toUrl);
				return false;
			}
		}
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public String getSessionAttr2() {
		return sessionAttr2;
	}

	public void setSessionAttr2(String sessionAttr2) {
		this.sessionAttr2 = sessionAttr2;
	}


	
}
