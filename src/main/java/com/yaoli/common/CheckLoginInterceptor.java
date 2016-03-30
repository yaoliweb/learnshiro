package com.yaoli.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger(CheckLoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		Subject subject = SecurityUtils.getSubject();
		String contextPath=request.getContextPath();
		if(subject.getPrincipal() == null){
			logger.info("用户没有登录");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			pw.print("<script>window.parent.location.href='"+contextPath + "/system/login.do'"+";</script>");
			return false;
		}
		return true;
	}

}
