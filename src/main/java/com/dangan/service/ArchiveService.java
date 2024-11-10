
package com.dangan.service;

import java.util.List;
import java.util.Map;
import com.dangan.entity.Archive;

/**
* 
* 
* 
*/
public interface ArchiveService {
	
	public void update(Archive archive);
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Archive> list(Map<String,Object> map,Integer page,Integer pageSize);
	
	public Long getTotal(Map<String,Object> map);
	
	
	
	
}
