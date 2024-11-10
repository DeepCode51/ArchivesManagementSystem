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
import com.dangan.dao.NewsDao;
import com.dangan.entity.News;
import com.dangan.service.NewsService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/news")
public class API_News_Controller {

	@Resource
	private NewsDao newsDao ;
	@Resource
	private NewsService newsService ;
	
	
	/**
	 *   /api/news/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(   News news  ,BindingResult bindingResult
			, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		news.setCreateDateTime(new Date());
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			newsDao.save(news);
			result.put("success", true);
			result.put("btn_disable", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 *   /api/news/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update(News news)throws Exception {
		JSONObject result = new JSONObject();
		newsService.update(news);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;
	}
	
	
	/**
	 *   /api/association/list
	 * @param page    默认1(layui传来的)
	 * @param limit   数据多少（每页数据）
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		Pageable pageable= PageRequest.of(page-1, limit, Sort.Direction.DESC,"id");
		Page<News> pagelist = newsDao.findAll(pageable);
		List<News> list = pagelist.getContent();//拿到list集合
		Long total =  newsDao.count();
		
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /api/association/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				newsDao.deleteById(Integer.parseInt(idsStr[i]));
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
	 * /api/news/findById
	 * 通过Id查找实体
	 * @param id
	 */
	@ResponseBody	
	@RequestMapping("/findById")
	public News findById(@RequestParam(value="id")Integer id,HttpServletResponse response)throws Exception{
		News news    =newsDao.findId(id);
		return news;
	}
	
	
	
	
	
	
}
