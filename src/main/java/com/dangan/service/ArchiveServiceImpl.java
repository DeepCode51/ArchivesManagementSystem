
package com.dangan.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.dangan.dao.ArchiveDao;
import com.dangan.dao.ConfigDao;
import com.dangan.entity.Archive;
import com.dangan.entity.News;
import com.dangan.entity.Archive;

/**
*
*
*/
@Service("archiveService")
public class ArchiveServiceImpl implements ArchiveService {
	
	@Resource
	private ArchiveDao archiveDao  ;
	
	
	
	@Override
	public List<Archive> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Archive> pages = archiveDao.findAll(new Specification<Archive>() {
			
			@Override
			public Predicate toPredicate(Root<Archive> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				
				// 加入 等于 
				if (map.get("user") != null) {
					predicate.getExpressions().add(cb.equal(root.get("user"), map.get("user")));
				}
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
		
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=archiveDao.count(new Specification<Archive>() {
			@Override
			public Predicate toPredicate(Root<Archive> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 加入 等于 
				if (map.get("user") != null) {
					predicate.getExpressions().add(cb.equal(root.get("user"), map.get("user")));
				}
				
				
				
				return predicate;
			}
		});
		return count;
	}
	
	@Override
	public void update(Archive archive) {
		Archive origin = archiveDao.findId(archive.getId());
		archive = repalce(archive, origin);
		archiveDao.save(archive);
	}
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Archive repalce(Archive curr,Archive origin){
		if(curr.getBianhao()   ==null){
			curr.setBianhao(origin.getBianhao());
		}
		if(curr.getName()  ==null){
			curr.setName(origin.getName());
		}
		if(curr.getUser() ==null){
			curr.setUser(origin.getUser());
		}
		
		
		if(curr.getDengji()  ==null){
			curr.setDengji(origin.getDengji());
		}
		if(curr.getGaokao()  ==null){
			curr.setGaokao(origin.getGaokao());
		}
		if(curr.getLuqu()  ==null){
			curr.setLuqu(origin.getLuqu());
		}
		if(curr.getTijian() ==null){
			curr.setTijian(origin.getTijian());
		}
		
		return curr;
	}
	
	
	
	
}
