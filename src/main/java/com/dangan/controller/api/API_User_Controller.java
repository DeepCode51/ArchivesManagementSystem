package com.dangan.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dangan.dao.UserDao;
import com.dangan.entity.User;
import com.dangan.service.UserService;
import com.dangan.util.CryptographyUtil;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/api/user")
public class API_User_Controller {
	
	@Resource
	private UserDao userDao ;
	@Resource
	private UserService userService ;
	
	/**
	 *   /api/user/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(  User user,BindingResult bindingResult
			,HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		user.setCreateDateTime(new Date());
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			//判断用户是否 存在
			User tUser = userDao.findByName(user.getName());
			if(tUser!=null) {
				result.put("success", false);
				result.put("msg", "用户已存在");
				return result;
			}
			//判断用户是否 存在
			
			userDao.save(user);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 *   /api/user/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update(  User user ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		userService.update(user);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;
	}
	
	/**
	 *   /api/user/update2
	 */
	@ResponseBody
	@RequestMapping("/update2")
	public JSONObject update2(  User user  )throws Exception {
		JSONObject result = new JSONObject();
		userService.update(user);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;
	}
	
	/**
	 *   /api/user/list
	 * @param page    默认1(layui传来的)
	 * @param limit   数据多少（每页数据）
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "state", required = false) Integer state, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(state!=null) {
			map.put("state", state);
		}
		
		List<User> list = userService.list(map, page-1, limit) ; 
		Long total =  userService.getTotal(map) ;
		
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /api/user/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				userDao.deleteById(Integer.parseInt(idsStr[i]));
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "删除错误");
				return result;
			}
		}
		result.put("success", true);
		return result;
	}
	
	/**
	 * /api/user/edit/pwd
	 */
	@ResponseBody
	@RequestMapping("/edit/pwd")
	public JSONObject edit_pwd(@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "yuan", required = false) String yuan
			,@RequestParam(value = "pwd", required = false) String pwd
			,HttpServletRequest request)throws Exception {
		JSONObject result = new JSONObject();
		
		User user = userDao.findId(userId);
		
		if(user.getPwd().equals(yuan)) {
			user.setPwd(pwd);
			userService.update(user); 
			result.put("success", true);
			result.put("msg", "修改成功");
		}else {
			result.put("success", false);
			result.put("msg", "原密码不对");
		}
		return result;
	}
	
	
	
}
