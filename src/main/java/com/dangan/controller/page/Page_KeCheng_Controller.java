
package com.dangan.controller.page;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dangan.dao.KeChengDao;
import com.dangan.dao.UserDao;
import com.dangan.entity.KeCheng;
import com.dangan.entity.User;


/**
*
*
*/
@Controller
public class Page_KeCheng_Controller {

	@Resource
	private KeChengDao keChengDao ;
	
	/**
	 * #课堂管理
	 */
	@RequestMapping("/admin/kecheng/manage")
	public ModelAndView teach__manage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "课程管理");
		mav.setViewName("/pc/admin/kecheng/kecheng_manage.html");
		return mav;
	}
	
	/**
	 *  /admin/kecheng/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/kecheng/add")
	public ModelAndView student_add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/api/kecheng/add");
		mav.setViewName("/pc/admin/kecheng/add_update.html");
		return mav;
	}
	
	
	/**
	 *  /admin/kecheng/edit?id=112
	 */
	@RequestMapping("/admin/kecheng/edit")
	public ModelAndView admin_student_eidt(@RequestParam(value = "id", required = false) Integer id,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		KeCheng kecheng  = keChengDao.findId(id);
		mav.addObject("save_url", "/api/kecheng/update?id="+id);
		mav.addObject("kecheng", kecheng);
		mav.addObject("btn_text", "修改");
		mav.setViewName("/pc/admin/kecheng/add_update.html");
		return mav;
	}
	
	
}
