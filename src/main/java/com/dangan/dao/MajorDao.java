
package com.dangan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dangan.entity.Major;

/**
* 
* 
*/
public interface MajorDao extends JpaRepository< Major,Integer>,JpaSpecificationExecutor< Major> {
	
	@Query(value="select * from t_major  where id = ?1",nativeQuery = true)
	public  Major findId(Integer id);
	
}
