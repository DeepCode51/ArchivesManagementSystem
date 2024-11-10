
package com.dangan.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dangan.dao.KeChengDao;
import com.dangan.dao.MessageDao;
import com.dangan.entity.KeCheng;
import com.dangan.entity.Message;
import com.dangan.entity.User;

import net.sf.json.JSONObject;

/**
* 
* 
* 
*/
@Controller
public class API_KeCheng_Controller {
	
	@Resource
	private KeChengDao keChengDao ;
	
	/**
	 *   /api/kecheng/add
	 */
	@ResponseBody
	@RequestMapping("/api/kecheng/add")
	public JSONObject add(   KeCheng kecheng    ,BindingResult bindingResult
			, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		keChengDao.save(kecheng);
		result.put("success", true);
		return result;
	}
	/**
	 *   /api/user/update
	 */
	@ResponseBody
	@RequestMapping("/api/kecheng/update")
	public JSONObject update(KeCheng kecheng   ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		keChengDao.save(kecheng);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;
	}
	
	
	
	/**
	 *   /api/kecheng/list
	 * @param page    默认1(layui传来的)
	 * @param limit   数据多少（每页数据）
	 */
	@ResponseBody
	@RequestMapping("/api/kecheng/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "userId", required = false) Integer userId, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(userId!=null) {
			User user = new User();
			user.setId(userId);
			map.put("user", user);
		}
		
		List<KeCheng> list = keChengDao.findAll()  ;//拿到list集合
		
		map.put("data", list);
		map.put("count", list.size());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * /api/kecheng/delete
	 */
	@ResponseBody
	@RequestMapping("/api/kecheng/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				keChengDao.deleteById(Integer.parseInt(idsStr[i]));
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
	
	
	
	
}
