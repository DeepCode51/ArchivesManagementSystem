package com.dangan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dangan.entity.Message;

public interface MessageDao extends JpaRepository< Message,Integer>,JpaSpecificationExecutor< Message> {
	
	@Query(value="select * from t_message  where id = ?1",nativeQuery = true)
	public  Message findId(Integer id);
	
	
}
