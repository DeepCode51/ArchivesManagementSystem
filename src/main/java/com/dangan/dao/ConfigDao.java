package com.dangan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dangan.entity.Config;



public interface ConfigDao  extends JpaRepository< Config,Integer>,JpaSpecificationExecutor< Config> {
	
	@Query(value="select * from t_config  where id = ?1",nativeQuery = true)
	public  Config findId(Integer id);
	
	
}
