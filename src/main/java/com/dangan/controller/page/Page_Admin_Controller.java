package com.dangan.controller.page;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.dangan.dao.UserDao;
import com.dangan.entity.User;


@Controller
public class Page_Admin_Controller {
	
	@Resource
	private UserDao userDao ;
	
	/**
	 * 注销
	 * /admin/logout
	 * @throws Exception
	 */
	@RequestMapping("/admin/logout")
	public String logout(HttpSession session)throws Exception{
		session.setAttribute("currentUser", null);
		return "redirect:/login";
	}
	
	/**
	 *  
	 */
	@RequestMapping("/admin/home")
	public ModelAndView admin_home(HttpServletResponse  res,HttpServletRequest req,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "个人中心");
		mav.setViewName("/pc/admin/home.html");
		return mav;
	}
	
	/**
	 */
	@RequestMapping("/admin/edit")
	public ModelAndView student_edit(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "修改资料");
		mav.setViewName("/pc/admin/edit.html");
		return mav;
	}
	
	
	
	/**
	 *  修改密码
	 */
	@RequestMapping("/admin/edit/pwd")
	public ModelAndView student_edit_pwd(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "修改密码");
		mav.setViewName("/pc/admin/edit_pwd.html");
		return mav;
	}
	

	
	/**
	 * 
	 *  # 活动管理
	 *  /admin/activity/list
	 *  
	 */
	@RequestMapping("/admin/activity/list")
	public ModelAndView admin_activity_list() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "活动管理");
		mav.setViewName("/pc/admin/activity/home.html");
		return mav;
	}
	
 
	
	
	/**
	 * /admin/message/manage
	 */
	@RequestMapping("/admin/message/manage")
	public ModelAndView message_manage(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title","留言管理");
		mav.setViewName("/pc/admin/message/manage.html");
		return mav;
	}
	
	
	
	
}
