package com.dangan.controller;

import java.net.Inet4Address;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dangan.dao.UserDao;
import com.dangan.entity.User;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserDao  userDao;
	
	/**
	 * /user/add
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(User user,HttpSession session)throws Exception {
		JSONObject result = new JSONObject();
		User tuser = userDao.findByName(user.getName());
		if(tuser!=null) {
			result.put("success", false);
			result.put("msg","用户名已存在");
			return result;
		}else {
			user.setCreateDateTime(new Date());
			user.setState(3);
			userDao.save(user);
			result.put("success", true);
			result.put("msg","注册成功");
			return result;
		}
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public JSONObject login(String name,String password,Integer state  ,HttpSession session)throws Exception {
		JSONObject result = new JSONObject();
		
		User user = userDao.findByName(name);
		
		if(user==null) {
			result.put("success", false);
			result.put("msg","用户名不存在");
		}else {
			if(user.getState()!=state) {
				//类型不是一样。
				result.put("success", false);
				result.put("msg","用户名不存在");
				return result;
			}
			if(password.equals(user.getPwd())) {
				result.put("success", true);
				result.put("msg","登陆成功");
				session.setAttribute("currentUser", user);//把当前用户信息存到session中
			}else {
				result.put("success", false);
				result.put("msg","密码错误");
			}
		}
		return result;
	}
	
	
 
	
	
}
