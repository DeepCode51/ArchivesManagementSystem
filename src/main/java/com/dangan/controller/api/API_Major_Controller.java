
package com.dangan.controller.api;

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
import com.dangan.dao.MajorDao;
import com.dangan.dao.MessageDao;
import com.dangan.entity.KeCheng;
import com.dangan.entity.Major;
import com.dangan.entity.User;
import net.sf.json.JSONObject;

/**
* 
* 
*/
@Controller
public class API_Major_Controller {
	
	@Resource
	private MajorDao majorDao   ;
	
	/**
	 *   /api/major/add
	 */
	@ResponseBody
	@RequestMapping("/api/major/add")
	public JSONObject add(Major major ,BindingResult bindingResult
			, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		majorDao.save(major);
		result.put("success", true);
		return result;
	}
	
	/**
	 *   /api/major/update
	 */
	@ResponseBody
	@RequestMapping("/api/major/update")
	public JSONObject update(Major major   ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		majorDao.save(major);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;
	}
	
	/**
	 *   /api/major/list
	 * @param page    默认1(layui传来的)
	 * @param limit   数据多少（每页数据）
	 */
	@ResponseBody
	@RequestMapping("/api/major/list")
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
		List<Major> list = majorDao.findAll()  ;//拿到list集合
		map.put("data", list);
		map.put("count", list.size());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * /api/major/delete
	 */
	@ResponseBody
	@RequestMapping("/api/major/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				majorDao.deleteById(Integer.parseInt(idsStr[i]));
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
