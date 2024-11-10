package com.dangan.controller.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
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
public class Page_User_Controller {
	
	@Resource
	private UserDao userDao ;
	@Resource
	private NewsDao newsDao;
	@Resource
	private MessageService messageService ;
	
	
	
	/**
	 * 注销
	 * /user/logout
	 * @throws Exception
	 */
	@RequestMapping("/user/logout")
	public String logout(HttpSession session)throws Exception{
		session.setAttribute("currentUser", null);
		return "redirect:/pc/user/login";
	}
	
	
	/**
	 *  #社长 主页。   /user/home
	 */
	@RequestMapping("/user/home")
	public ModelAndView user_home(HttpServletResponse  res,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "个人中心");
		
		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.setViewName("/pc/shezhang/home.html");
		return mav;
	}
	
	/**
	 */
	@RequestMapping("/user/edit")
	public ModelAndView user_edit(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "修改资料");
		mav.setViewName("/pc/shezhang/edit.html");
		return mav;
	}
	
	
	
	/**
	 */
	@RequestMapping("/user/edit/pwd")
	public ModelAndView user_edit_pwd(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "修改密码");
		mav.setViewName("/pc/shezhang/edit_pwd.html");
		return mav;
	}
	
	
	/**
	 *  社团申请记录
	 *  /user/apply/list
	 */
	@RequestMapping("/user/apply/list")
	public ModelAndView user_apply_list(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
	 
		mav.addObject("title","社团申请记录");
		mav.setViewName("/pc/shezhang/apply/list.html");
		return mav;
	}
	
	
	/**
	 *  社团成员
	 *  /user/member/list
	 */
	@RequestMapping("/user/member/list")
	public ModelAndView user_member_list(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("title","社团成员");
		mav.setViewName("/pc/shezhang/member_list.html");
		return mav;
	}
	
	/**
	 *  新闻中心
	 *  /user/news
	 */
	@RequestMapping("/user/news")
	public ModelAndView user_news(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("title","新闻中心");
		mav.setViewName("/pc/shezhang/news/home.html");
		return mav;
	}
	
	/**
	 * /user/news/view?id=1
	 */
	@RequestMapping("/user/news/view")
	public ModelAndView news_view(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		News news     = newsDao.findId(id);
		mav.addObject("news", news);
		mav.addObject("title",news.getTitle());
		mav.setViewName("/pc/shezhang/news/view.html");
		return mav;
	}
	
	
 
	 
	 
	 
	
	/**
	 * /user/message
	 */
	@RequestMapping("/user/message")
	public ModelAndView user_message() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title","留言板");
		
		Map<String, Object> map = new  HashMap<String, Object>();
		List<Message> messageList= messageService.list(map , 0, 200);
		mav.addObject("messageList", messageList);
		
		mav.setViewName("/pc/shezhang/message/index.html");
		return mav;
	}
	
	
	
	
}
