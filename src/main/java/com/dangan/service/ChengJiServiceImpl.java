
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

import com.dangan.dao.ChengJiDao;
import com.dangan.entity.ChengJi;

/**
*  
*/

@Service("chengJiService")
public class ChengJiServiceImpl implements ChengJiService {
	
	@Resource
	private ChengJiDao chengJiDao ;

	@Override
	public void update(ChengJi chengji) {
		ChengJi origin = chengJiDao.findId(chengji.getId());
		chengji = repalce(chengji, origin);
		chengJiDao.save(chengji);
	}
	
	
	@Override
	public List<ChengJi> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<ChengJi> pages = chengJiDao.findAll(new Specification<ChengJi>() {
			@Override
			public Predicate toPredicate(Root<ChengJi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				 
				// 加入 等于 
				if (map.get("user") != null) {
					predicate.getExpressions().add(cb.equal(root.get("user"), map.get("user")));
				}
				// 模糊查询
				if (map.get("kemu") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("kemu"),"%"+map.get("kemu").toString()+"%") 
							));
				}
				
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
		
	}
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		
		Long count=chengJiDao.count(new Specification<ChengJi>() {
			@Override
			public Predicate toPredicate(Root<ChengJi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				// 加入 等于 
				if (map.get("user") != null) {
					predicate.getExpressions().add(cb.equal(root.get("user"), map.get("user")));
				}
				// 模糊查询
				if (map.get("kemu") != null) {
					predicate.getExpressions().add(cb.or(
							cb.like(root.get("kemu"),"%"+map.get("kemu").toString()+"%") 
							));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public ChengJi repalce(ChengJi curr,ChengJi origin){
		if(curr.getKemu()  ==null){
			curr.setKemu(origin.getKemu());
		}
		if(curr.getScore()  ==null){
			curr.setScore(origin.getScore());
		}
		if(curr.getUser()   ==null){
			curr.setUser(origin.getUser());
		}
		return curr;
	}
	
	
	
}
