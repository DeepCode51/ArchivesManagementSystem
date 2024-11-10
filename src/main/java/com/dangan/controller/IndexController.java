package com.dangan.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import net.sf.json.JSONObject;

@Controller
public class IndexController {
	
	
	/**
	 *# 请求首页  
	 */
	@RequestMapping("/")
	public String  index_1(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		return "redirect:/login";
	}
	
	/**
	 *   #请求首页  /index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		return "redirect:/login";
	}
	
	
	/**
	 *     登陆
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "登录") ;
		mav.setViewName("/pc/login/login");
		return mav;
	}
	
	
}
