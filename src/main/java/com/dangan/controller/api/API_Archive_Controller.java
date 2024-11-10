
package com.dangan.controller.api;


import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import com.dangan.dao.ArchiveDao;
import com.dangan.dao.KeChengDao;
import com.dangan.entity.Archive;
import com.dangan.entity.KeCheng;
import com.dangan.entity.User;
import com.dangan.service.ArchiveService;
import com.dangan.util.DateUtil;
import com.dangan.util.FileUtil;
import com.dangan.util.MyUtil;

import net.sf.json.JSONObject;


/**
* 
*/
@Controller
public class API_Archive_Controller {
	
	@Resource
	private ArchiveDao archiveDao   ;
	@Resource
	private ArchiveService  archiveService   ;
	
	/**
	 *   /api/archive/add
	 */
	@ResponseBody
	@RequestMapping("/api/archive/add")
	public JSONObject api_archive_add(   Archive archive      ,BindingResult bindingResult
			, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user",archive.getUser());
		Long total = archiveService.getTotal(map );
		if(total>0) {
			result.put("success", false);
			result.put("msg", "该学生已创建档案，无须重复创建。");
			return result;
		}
		
		archiveDao.save(archive);
		result.put("success", true);
		
		return result;
	}
	
	/**
	 *   /api/archive/update
	 */
	@ResponseBody
	@RequestMapping("/api/archive/update")
	public JSONObject api_archive_update( Archive archive    ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		archiveService.update(archive);
		result.put("success", true);
		result.put("msg", "修改成功");
		return result;
	}
	
	
	/**
	 *   /api/archive/list
	 * @param page    默认1(layui传来的)
	 * @param limit   数据多少（每页数据）
	 */
	@ResponseBody
	@RequestMapping("/api/archive/list")
	public Map<String, Object> api_archive_list(@RequestParam(value = "page", required = false) Integer page,
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
		
		List<Archive> list = archiveService.list(map, page-1, limit)  ;// 
		Long total = archiveService.getTotal(map);
		
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /api/archive/delete
	 */
	@ResponseBody
	@RequestMapping("/api/archive/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				archiveDao.deleteById(Integer.parseInt(idsStr[i]));
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
	 * /api/archive/add/file
	 */
	@ResponseBody
	@RequestMapping("/api/archive/add/file")
	public JSONObject api_archive_add_file(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {
	        JSONObject result = new JSONObject();
	        if(!file.isEmpty()){
	            //String webPath=request.getServletContext().getRealPath("");
	            String webPath=MyUtil.uploadPath;
	            
	            System.out.println(webPath);
	            String filePath= "/static/upload_file/luqu/";
	            //把文件名子换成（时间搓.png）
	            // String imageName="houtai_logo."+file.getOriginalFilename().split("\\.")[1];
	            //检测   文件夹有没有创建 
	            FileUtil.makeDirs(webPath+filePath);
	            //String imageName=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+".jpg";
	            String file_name=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+"__"+file.getOriginalFilename().split("\\.")[0]+"."+file.getOriginalFilename().split("\\.")[1];
	            
	            file.transferTo(new File(webPath+filePath+file_name));
	            result.put("success", true);
	            result.put("path", "/upload_load"+filePath+file_name);
	        }
	        
	        return result;
	}
	
	/**
	 * /api/archive/add/file2
	 */
	@ResponseBody
	@RequestMapping("/api/archive/add/file2")
	public JSONObject api_archive_add_file2(@RequestParam("file2") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {
	        JSONObject result = new JSONObject();
	        if(!file.isEmpty()){
	            String webPath=request.getServletContext().getRealPath("");
	            System.out.println(webPath);
	            String filePath= "/static/upload_file/tijian/";
	            //把文件名子换成（时间搓.png）
	            // String imageName="houtai_logo."+file.getOriginalFilename().split("\\.")[1];
	            //检测   文件夹有没有创建 
	            FileUtil.makeDirs(webPath+filePath);
	            //String imageName=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+".jpg";
	            String file_name=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+"__"+file.getOriginalFilename().split("\\.")[0]+"."+file.getOriginalFilename().split("\\.")[1];
	            
	            file.transferTo(new File(webPath+filePath+file_name));
	            result.put("success", true);
	            result.put("path", "/upload_load"+filePath+file_name);
	        }
	        
	        return result;
	}
	
	
	/**
	 * /api/archive/add/file3
	 */
	@ResponseBody
	@RequestMapping("/api/archive/add/file3")
	public JSONObject api_archive_add_file3(@RequestParam("file3") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {
	        JSONObject result = new JSONObject();
	        if(!file.isEmpty()){
	            String webPath=request.getServletContext().getRealPath("");
	            System.out.println(webPath);
	            String filePath= "/static/upload_file/gaokao/";
	            //把文件名子换成（时间搓.png）
	            // String imageName="houtai_logo."+file.getOriginalFilename().split("\\.")[1];
	            //检测   文件夹有没有创建 
	            FileUtil.makeDirs(webPath+filePath);
	            //String imageName=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+".jpg";
	            String file_name=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+"__"+file.getOriginalFilename().split("\\.")[0]+"."+file.getOriginalFilename().split("\\.")[1];
	            
	            file.transferTo(new File(webPath+filePath+file_name));
	            result.put("success", true);
	            result.put("path", "/upload_load"+filePath+file_name);
	        }
	        
	        return result;
	}
	
	/**
	 * /api/archive/add/file4
	 */
	@ResponseBody
	@RequestMapping("/api/archive/add/file4")
	public JSONObject api_archive_add_file4(@RequestParam("file4") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {
	        JSONObject result = new JSONObject();
	        if(!file.isEmpty()){
	            String webPath=request.getServletContext().getRealPath("");
	            System.out.println(webPath);
	            String filePath= "/static/upload_file/dengji/";
	            //把文件名子换成（时间搓.png）
	            // String imageName="houtai_logo."+file.getOriginalFilename().split("\\.")[1];
	            //检测   文件夹有没有创建 
	            FileUtil.makeDirs(webPath+filePath);
	            //String imageName=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+".jpg";
	            String file_name=DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+"__"+file.getOriginalFilename().split("\\.")[0]+"."+file.getOriginalFilename().split("\\.")[1];
	            
	            file.transferTo(new File(webPath+filePath+file_name));
	            result.put("success", true);
	            result.put("path", "/upload_load"+filePath+file_name);
	        }
	        
	        return result;
	}
	
	
}
