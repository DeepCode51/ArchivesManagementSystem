
package com.dangan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dangan.entity.ChengJi;

/**
*/
public interface ChengJiDao    extends JpaRepository<ChengJi,Integer>,JpaSpecificationExecutor<ChengJi> {
	
	@Query(value="select * from t_chengji  where id = ?1",nativeQuery = true)
	public ChengJi findId(Integer id);
	
}
