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

import com.dangan.dao.MessageDao;
import com.dangan.entity.Message;
import com.dangan.entity.User;
import com.dangan.service.MessageService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/message")
public class API_Message_Controller {
	
	@Resource
	private MessageDao messageDao   ;
	@Resource
	private MessageService messageService    ;
	
	/**
	 *   
	 *   /api/message/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(   Message message  ,BindingResult bindingResult
			, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		message.setCreateDateTime(new Date());
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			messageDao.save(message);
			result.put("success", true);
			result.put("msg", "申请已提交");
			return result;
		}
	}
	
	/**
	 *   /api/message/list
	 * @param page    默认1(layui传来的)
	 * @param limit   数据多少（每页数据）
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "userId", required = false) Integer userId, 
			@RequestParam(value = "associationId", required = false) Integer associationId, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(userId!=null) {
			User user = new User();
			user.setId(userId);
			map.put("user", user);
		}
		
		List<Message> list = messageService.list(map, page-1, limit)  ;//拿到list集合
		Long total =  messageService.getTotal(map);
		
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /api/message/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				messageDao.deleteById(Integer.parseInt(idsStr[i]));
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
