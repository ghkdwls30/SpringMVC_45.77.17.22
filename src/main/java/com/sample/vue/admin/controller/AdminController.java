package com.sample.vue.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView moveListPage(HttpServletRequest req
									, HttpServletResponse res
									, @ModelAttribute PageCriteria pageCriteria
									, @RequestParam Map<String, Object> param) throws Exception {
		ModelAndView m = new ModelAndView();
		m.setViewName("admin.userListContent.base");
		
		if( pageCriteria.getPage() == null || pageCriteria.getSize() == null) {
			pageCriteria.setPage(1);
			pageCriteria.setSize(20);
		}
		
		PageInfo<UserEntity> userEntityPageList = userService.selectUserListPage(param, pageCriteria);
		
		m.addObject("userEntityList", userEntityPageList.getList());
		m.addObject("pageInfo", userEntityPageList);
		
		return m;
	}
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public ModelAndView moveRegistPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView m = new ModelAndView();
		m.setViewName("admin.userRegistContent.base");
		return m;
	}
	
	@RequestMapping(value = "/user/edit/{account}", method = RequestMethod.GET)
	public ModelAndView moveEditPage(HttpServletRequest req, HttpServletResponse res, @PathVariable String account) throws Exception {
		ModelAndView m = new ModelAndView();
		m.setViewName("admin.userEditContent.base");
		m.addObject("userEntity", userService.selectUser(account));
		return m;
	}
	
}
