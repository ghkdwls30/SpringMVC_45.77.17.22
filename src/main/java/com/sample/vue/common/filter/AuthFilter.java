package com.sample.vue.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample.vue.user.model.UserEntity;

public class AuthFilter implements javax.servlet.Filter {
	  
	
	public static String RESOURCE_PATH = "/resources";
	public static String IMAGE_PATH = "/img";
	public static String CSS_PATH = "/css";
	public static String JS_PATH = "/js";
	public static String DIST_PATH = "/dist";
	public static String ASSETS_PATH = "/assets";
	public static String FONT_AWESOME_PATH = "/font-awesome";
	public static String WEBJARS_PATH = "/webjars";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession(true);
		
		UserEntity userEntity = (UserEntity)session.getAttribute("userEntity");
		
		
		if( 	userEntity == null && 
				!req.getRequestURI().equals("/") && 
				!req.getRequestURI().equals("/login") &&
				!req.getRequestURI().startsWith(RESOURCE_PATH) &&
				!req.getRequestURI().startsWith(IMAGE_PATH) &&
				!req.getRequestURI().startsWith(CSS_PATH) &&
				!req.getRequestURI().startsWith(JS_PATH) &&
				!req.getRequestURI().startsWith(DIST_PATH) &&
				!req.getRequestURI().startsWith(ASSETS_PATH) &&
				!req.getRequestURI().startsWith(FONT_AWESOME_PATH) &&
				!req.getRequestURI().startsWith(WEBJARS_PATH)
				) {
			((HttpServletResponse) response).sendRedirect("/");
		}else {
			chain.doFilter(request, response); 
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
 }
