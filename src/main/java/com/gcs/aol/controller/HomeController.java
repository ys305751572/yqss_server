package com.gcs.aol.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/management/home")
public class HomeController {
	
	private static final String ORG_HOME_PAGE = "management/index/org_home";
	
	/**
	 * 跳转到首页面（总公司和代理商）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orgHomePage", method=RequestMethod.GET)
	public String orgHomePage(HttpServletRequest request) {
		return ORG_HOME_PAGE;
	}
}
