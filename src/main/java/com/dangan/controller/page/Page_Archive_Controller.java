
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

import com.dangan.dao.ArchiveDao;
import com.dangan.dao.UserDao;
import com.dangan.entity.Archive;
import com.dangan.entity.ChengJi;
import com.dangan.entity.Major;
import com.dangan.entity.User;
import com.dangan.service.ChengJiService;
import com.dangan.service.UserService;

/**
* 
*/
@Controller
public class Page_Archive_Controller {
	
	@Resource
	private UserService userService   ;
	@Resource
	private ArchiveDao archiveDao ;
	@Resource
	private ChengJiService chengJiService   ;
	
	/**
	 * 创建档案
	 *  /admin/archive/create
	 */
	@RequestMapping("/admin/archive/create")
	public ModelAndView archive_create() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state",3);//1管理员   2教师   3 学生
		List<User>  userList =   userService.list(map , 0, 200);
		mav.addObject("userList", userList);
		
		mav.setViewName("/pc/admin/archive/create.html");
		return mav;
	}
	
	
	/**
	 *  /admin/major/edit?id=112
	 */
	@RequestMapping("/admin/archive/edit")
	public ModelAndView major_eidt(@RequestParam(value = "id", required = false) Integer id,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		Archive archive  = archiveDao.findId(id);
		mav.addObject("save_url", "/api/major/update?id="+id);
		mav.addObject("archive", archive);
		mav.addObject("btn_text", "修改");
		mav.setViewName("/pc/admin/archive/edit.html");
		return mav;
	}
	
	
	/**
	 *  /admin/major/view?id=112
	 */
	@RequestMapping("/admin/archive/view")
	public ModelAndView major_view(@RequestParam(value = "id", required = false) Integer id,HttpSession session  ) throws Exception {
		ModelAndView mav = new ModelAndView();
		Archive archive  = archiveDao.findId(id);
		mav.addObject("archive", archive);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", archive.getUser());
		List<ChengJi> chengjiList = chengJiService.list(map , 0, 500);
		mav.addObject("chengjiList", chengjiList);
		
		
		mav.setViewName("/pc/admin/archive/view.html");
		return mav;
	}
	
	
	/**
	 * #档案管理
	 */
	@RequestMapping("/admin/archive/manage")
	public ModelAndView archive_manage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "档案管理");
		mav.setViewName("/pc/admin/archive/archive_manage.html");
		return mav;
	}
	
	
	
	
}
