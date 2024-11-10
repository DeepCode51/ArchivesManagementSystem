
package com.dangan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dangan.entity.KeCheng;
import com.dangan.entity.Message;

/**
* 
* 
*/
public interface KeChengDao  extends JpaRepository< KeCheng,Integer>,JpaSpecificationExecutor< KeCheng> {
	
	@Query(value="select * from t_kecheng  where id = ?1",nativeQuery = true)
	public  KeCheng findId(Integer id);

}
