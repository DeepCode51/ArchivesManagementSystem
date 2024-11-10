
package com.dangan.controller.page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dangan.dao.UserDao;
import com.dangan.entity.User;

/**
* 
*/
@Controller
public class Page_Teach_Controller {
	@Resource
	private UserDao userDao  ;
	
	
	/**
	 * 
	 * #教师管理
	 */
	@RequestMapping("/page/teach/manage")
	public ModelAndView teach__manage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "教师管理");
		mav.setViewName("/pc/admin/teach/manage.html");
		return mav;
	}
	
	/**
	 * /page/teach/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/page/teach/add")
	public ModelAndView teach_add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/api/user/add");
		mav.setViewName("/pc/admin/teach/add_update.html");
		return mav;
	}
	
	/**
	 * /page/teach/edit?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/page/teach/edit")
	public ModelAndView teach_edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user   = userDao.findId(id);
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/api/user/update?id=" + id);
		mav.setViewName("/pc/admin/teach/add_update.html");
		return mav;
	}
	
	/**
	 *  /page/teach/set_new_pwd?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/page/teach/set_new_pwd")
	public ModelAndView  teach_set_new_pwd(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = userDao.findId(id);
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/api/user/update2?id=" + id);
		mav.setViewName("/pc/admin/teach/set_new_pwd.html");
		return mav;
	}
	
	
	
	
	
}
