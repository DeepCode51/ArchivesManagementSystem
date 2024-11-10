package com.dangan.controller.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.dangan.dao.NewsDao;
import com.dangan.entity.News;
import com.dangan.entity.User;

@Controller
public class Page_News_Controller {
	@Resource
	private NewsDao newsDao;
	
	
	/**
	 *   /page/news/home
	 */
	@RequestMapping("/page/news/home")
	public ModelAndView news_home(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "新闻管理");
		mav.setViewName("/pc/admin/news/home.html");
		return mav;
	}
	
	/**
	 * /page/news/add
	 */
	@RequestMapping("/page/news/add")
	public ModelAndView news_add(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "添加新闻");
		
		mav.addObject("btn_text", "提交");
		mav.addObject("save_url", "/api/news/add");
		mav.addObject("isEdit", false);
		
		mav.setViewName("/pc/admin/news/add_update.html");
		return mav;
	}
		
	
	/**
	 * /page/news/edit?id=1
	 */
	@RequestMapping("/page/news/edit")
	public ModelAndView news_edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		News news     = newsDao.findId(id);
		mav.addObject("news", news);
		mav.addObject("title", "修改新闻");
		
		mav.addObject("isEdit", true);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/api/news/update?id=" + id);
		 
		mav.setViewName("/pc/admin/news/add_update.html");
		return mav;
	}
	
	/**
	 * /page/news/view?id=1
	 */
	@RequestMapping("/page/news/view")
	public ModelAndView news_view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		News news     = newsDao.findId(id);
		mav.addObject("news", news);
		mav.addObject("title",news.getTitle());
		
		mav.setViewName("/pc/admin/news/view.html");
		return mav;
	}
	
	
}
