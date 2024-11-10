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

import com.dangan.dao.MessageDao;
import com.dangan.dao.NewsDao;
import com.dangan.entity.Message;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageDao messageDao    ;
	
	
	@Override
	public List<Message> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Message> pages = messageDao.findAll(new Specification<Message>() {
			
			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				
				// 加入 等于 
				if (map.get("association") != null) {
					predicate.getExpressions().add(cb.equal(root.get("association"), map.get("association")));
				}
				
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
		Long count=messageDao.count(new Specification<Message>() {
			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				
				// 加入 等于 
				if (map.get("association") != null) {
					predicate.getExpressions().add(cb.equal(root.get("association"), map.get("association")));
				}
				// 加入 等于 
				if (map.get("user") != null) {
					predicate.getExpressions().add(cb.equal(root.get("user"), map.get("user")));
				}
				
				
				return predicate;
			}
		});
		return count;
	}
	
	
	
	
}
