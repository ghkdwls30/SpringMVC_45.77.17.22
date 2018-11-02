package com.sample.vue.common.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sample.vue.user.model.UserEntity;

public class AuthUtil {

	public static boolean isAdmin() {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
		if( ((UserEntity)httpSession.getAttribute("userEntity")).getType().equals("01")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static UserEntity getCurrentUserEntitiy() {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
	    return (UserEntity)httpSession.getAttribute("userEntity");
	}
	

	public static String getCurrentUserAccount() {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
	    return( (UserEntity)httpSession.getAttribute("userEntity")).getAccount();
	}
}
