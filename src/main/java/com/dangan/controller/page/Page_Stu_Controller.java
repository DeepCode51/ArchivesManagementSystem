
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

import com.dangan.dao.ArchiveDao;
import com.dangan.dao.UserDao;
import com.dangan.entity.Archive;
import com.dangan.entity.ChengJi;
import com.dangan.entity.User;
import com.dangan.service.ChengJiService;

/**
 * 
*/
@Controller
public class Page_Stu_Controller {
	
	@Resource
	private UserDao userDao ;
	@Resource
	private ArchiveDao archiveDao   ;
	@Resource
	private ChengJiService chengJiService   ;
	
	
	/**
	 *  #学生 管理
	 */
	@RequestMapping("/admin/student/manage")
	public ModelAndView admin_student_manage(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "学生管理");
		mav.setViewName("/pc/admin/student/manage.html");
		return mav;
	}
	
	/**
	 *  /admin/student/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/student/add")
	public ModelAndView student_add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/api/user/add");
		mav.setViewName("/pc/admin/student/add_update.html");
		return mav;
	}
	/**
	 *  #修改 学生
	 *  /admin/student/edit?userId=112
	 */
	@RequestMapping("/admin/student/edit")
	public ModelAndView admin_student_eidt(@RequestParam(value = "userId", required = false) Integer userId,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user  = userDao.findId(userId);
		mav.addObject("save_url", "/api/user/update?id="+user.getId());
		mav.addObject("user", user);
		mav.setViewName("/pc/admin/student/add_update.html");
		return mav;
	}
	
	
	
	/**
	 *  /page/student/set_new_pwd?userId=1
	 *   修改 学生  密码
	 *  
	 */
	@RequestMapping("/page/student/set_new_pwd")
	public ModelAndView admin_stu_set_new_pwd(@RequestParam(value = "userId", required = false) Integer userId) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = userDao.findId(userId);
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/api/user/update2?id=" + userId);
		mav.setViewName("/pc/admin/student/set_new_pwd.html");
		return mav;
	}
	
	
	
	/**
	 *  #学生主页个人中心。
	 */
	@RequestMapping("/student/home")
	public ModelAndView student_home(HttpServletResponse  res,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "个人中心");
		
		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.setViewName("/pc/student/home.html");
		return mav;
	}
	
	/**
	 *  学生主页的注销
	 * /student/logout
	 * @throws Exception
	 */
	@RequestMapping("/student/logout")
	public String student_logout(HttpSession session)throws Exception{
		session.setAttribute("currentUser", null);
		return "redirect:/login";
	}
	
	/**
	 * 学生自己修改资料
	 * 
	 */
	@RequestMapping("/student/edit")
	public ModelAndView student_edit(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "修改资料");
		mav.setViewName("/pc/student/edit.html");
		return mav;
	}
	/**
	 * 学生修改密码
	 */
	@RequestMapping("/student/edit/pwd")
	public ModelAndView student_edit_pwd(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("currentUser");
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "修改密码");
		mav.setViewName("/pc/student/edit_pwd.html");
		return mav;
	}
	
	
	/**
	 * 学生查看自己的档案
	 */
	@RequestMapping("/student/dang/an")
	public ModelAndView student_dang_an(HttpServletResponse  res,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User) session.getAttribute("currentUser");
		Archive archive  = archiveDao.findByUser(user);
		mav.addObject("archive",archive);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", archive.getUser());
		List<ChengJi> chengjiList = chengJiService.list(map , 0, 500);
		mav.addObject("chengjiList", chengjiList);
		
		
		user = userDao.findId(user.getId());
		session.setAttribute("currentUser", user);
		
		mav.addObject("title", "学生档案");
		mav.setViewName("/pc/student/dangan.html");
		return mav;
	}
	
	
}
