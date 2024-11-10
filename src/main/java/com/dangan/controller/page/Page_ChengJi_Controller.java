
package com.dangan.controller.page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dangan.dao.ChengJiDao;
import com.dangan.dao.KeChengDao;
import com.dangan.entity.ChengJi;
import com.dangan.entity.KeCheng;
import com.dangan.entity.User;
import com.dangan.service.UserService;



@Controller
public class Page_ChengJi_Controller {
	
	@Resource
	private ChengJiDao chengJiDao ;
	@Resource
	private UserService userService ;
	
	
	/**
	 * #成绩管理
	 */
	@RequestMapping("/admin/chengji/manage")
	public ModelAndView chengji_manage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 3);
		List<User> userList= userService.list(map, 0, 1000);
		mav.addObject("userList",userList);
		
		
		mav.addObject("title", "成绩管理");
		mav.setViewName("/pc/admin/chengji/chengji_manage.html");
		return mav;
	}
	
	
	/**
	 *  /admin/chengji/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/chengji/add")
	public ModelAndView chengji_add() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 3);
		List<User> userList= userService.list(map, 0, 1000);
		mav.addObject("userList",userList);
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/api/chengji/add");
		mav.setViewName("/pc/admin/chengji/add_update.html");
		return mav;
	}
	
	
	/**
	 *  /admin/chengji/edit?id=112
	 */
	@RequestMapping("/admin/chengji/edit")
	public ModelAndView  chengji_eidt(@RequestParam(value = "id", required = false) Integer id,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		ChengJi chengji  = chengJiDao.findId(id);
		mav.addObject("save_url", "/api/chengji/update?id="+id);
		mav.addObject("chengji", chengji);
		mav.addObject("btn_text", "修改");
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 3);
		List<User> userList= userService.list(map, 0, 1000);
		mav.addObject("userList",userList);
		
		mav.setViewName("/pc/admin/chengji/add_update.html");
		return mav;
	}
	
	
	/**
	 * #成绩管理
	 */
	@RequestMapping("/stu/chengji/manage")
	public ModelAndView stu_chengji_manage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 3);
		List<User> userList= userService.list(map, 0, 1000);
		mav.addObject("userList",userList);
		
		
		mav.addObject("title", "成绩管理");
		mav.setViewName("/pc/student/chengji/chengji_manage.html");
		return mav;
	}
	
	
	
}
