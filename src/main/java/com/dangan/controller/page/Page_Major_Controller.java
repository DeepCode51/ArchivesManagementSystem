
package com.dangan.controller.page;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dangan.dao.KeChengDao;
import com.dangan.dao.MajorDao;
import com.dangan.dao.UserDao;
import com.dangan.entity.KeCheng;
import com.dangan.entity.Major;
import com.dangan.entity.User;


/**
*
*
*/
@Controller
public class Page_Major_Controller {

	@Resource
	private MajorDao majorDao   ;
	
	/**
	 * #专业管理
	 */
	@RequestMapping("/admin/major/manage")
	public ModelAndView major_manage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "专业管理");
		mav.setViewName("/pc/admin/major/major_manage.html");
		return mav;
	}
	
	/**
	 *  /admin/major/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/major/add")
	public ModelAndView major_add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/api/major/add");
		mav.setViewName("/pc/admin/major/add_update.html");
		return mav;
	}
	
	
	/**
	 *  /admin/major/edit?id=112
	 */
	@RequestMapping("/admin/major/edit")
	public ModelAndView major_eidt(@RequestParam(value = "id", required = false) Integer id,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		Major major  = majorDao.findId(id);
		mav.addObject("save_url", "/api/major/update?id="+id);
		mav.addObject("major", major);
		mav.addObject("btn_text", "修改");
		mav.setViewName("/pc/admin/major/add_update.html");
		return mav;
	}
	
	
}
