package com.sample.vue.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 로그인 페이지 이동
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String moveLoginPage(HttpServletRequest req, HttpServletResponse res) {
		UserEntity userEntity = (UserEntity)req.getSession().getAttribute("userEntity");
		if( userEntity != null) {
			return "redirect:/home/main";
		}else {
			return "login.loginContent.single";
		}
	}
	
	
	/**
	 * 로그인 처리
	 * @param req
	 * @param res
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String,Object> param, RedirectAttributes redirectAttributes) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = userService.selectUserByAccountAndPassword(param);
		
 		if( userEntity == null) {
			redirectAttributes.addAttribute("successYn", "N");
			return "redirect:/";
		}else {
			mav.setViewName("login.loginContent.single");
			req.getSession().setAttribute("userEntity", userEntity);
			return "redirect:/home/main";
		}
	}
	
	
	/**
	 * 로그아웃 처리
	 * @param req
	 * @param res
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String doLogout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession().invalidate();
		return "login.loginContent.single";
	}
	
}
