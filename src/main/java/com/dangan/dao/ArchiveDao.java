
package com.dangan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.dangan.entity.Archive;
import com.dangan.entity.User;
/**
* 
* 
* 
*/
public interface ArchiveDao  extends JpaRepository< Archive,Integer>,JpaSpecificationExecutor< Archive> {
	
	@Query(value="select * from t_archive  where id = ?1",nativeQuery = true)
	public  Archive findId(Integer id);
	
	
	public  Archive  findByUser(User user);
}
