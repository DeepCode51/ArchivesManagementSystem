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

import com.dangan.dao.UserDao;
import com.dangan.entity.User;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao  userDao;
	
	
	@Override
	public void update(User user ) {
		User origin = userDao.findId(user.getId());
		user = repalce(user, origin);
		userDao.save(user);
	}
	
	@Override
	public List<User> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<User> pages = userDao.findAll(new Specification<User>() {
			
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				
				// 加入 等于 
				if (map.get("state") != null) {
					predicate.getExpressions().add(cb.equal(root.get("state"), map.get("state")));
				}
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=userDao.count(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();

				// 加入 等于 
				if (map.get("state") != null) {
					predicate.getExpressions().add(cb.equal(root.get("state"), map.get("state")));
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
	public User repalce(User curr,User origin){
		
		if(curr.getName()==null){
			curr.setName(origin.getName());
		}
		if(curr.getPwd()==null){
			curr.setPwd(origin.getPwd());
		}
		if(curr.getTrueName()==null){
			curr.setTrueName(origin.getTrueName());
		}
		if(curr.getRemark()==null){
			curr.setRemark(origin.getRemark());
		}
		
		
		
		if(curr.getCreateDateTime()==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		if(curr.getState() ==null){
			curr.setState(origin.getState());
		}
		if(curr.getSex()  ==null){
			curr.setSex(origin.getSex());
		}
		if(curr.getPhone()  ==null){
			curr.setPhone(origin.getPhone());
		}
		
		return curr;
	}

	
	
	
	
}
