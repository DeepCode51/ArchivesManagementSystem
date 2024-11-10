package com.dangan.controller.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dangan.dao.NewsDao;
import com.dangan.dao.UserDao;
import com.dangan.entity.Message;
import com.dangan.entity.News;
import com.dangan.entity.User;
import com.dangan.service.MessageService;

@Controller
public class Page_Student_Controller {
	
	@Resource
	private UserDao userDao ;
	@Resource
	private NewsDao newsDao;
	@Resource
	private MessageService messageService ;

	
	
	/**
	 */
	@RequestMapping("/student/news")
	public ModelAndView student_news(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "新闻中心");
		mav.setViewName("/pc/student/news/home.html");
		return mav;
	}
	
	/**
	 * /student/news/view?id=1
	 */
	@RequestMapping("/student/news/view")
	public ModelAndView news_view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		News news     = newsDao.findId(id);
		mav.addObject("news", news);
		mav.addObject("title",news.getTitle());
		
		mav.setViewName("/pc/student/news/view.html");
		return mav;
	}
 
 
	
 
	 
	/**
	 *  /student/message
	 */
	@RequestMapping("/student/message")
	public ModelAndView student_message() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title","留言板");
		
		Map<String, Object> map = new  HashMap<String, Object>();
		List<Message> messageList= messageService.list(map , 0, 200);
		mav.addObject("messageList", messageList);
		
		mav.setViewName("/pc/student/message/index.html");
		return mav;
	}
	
			
	
}
