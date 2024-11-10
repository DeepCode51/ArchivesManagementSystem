package com.dangan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dangan.entity.News;

public interface NewsDao extends JpaRepository<News,Integer>,JpaSpecificationExecutor<News> {
	
	@Query(value="select * from t_news where id = ?1",nativeQuery = true)
	public News findId(Integer id);
	
}
