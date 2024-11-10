package com.dangan.service;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dangan.dao.ConfigDao;
import com.dangan.entity.Config;
import com.dangan.util.MyUtil;


@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private ConfigDao configDao;
	
	@Value("${uploadPath}")
	private String uploadPath;
	
	
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Config repalce(Config curr,Config origin){
		if(curr.getWebName() ==null){
			curr.setWebName(origin.getWebName());
		}
		 
		return curr;
	}
	
	@Override
	public void update(Config config) {
		Config origin = configDao.findId(config.getId());
		config = repalce(config, origin);
		configDao.save(config);
	}
	
	/**
	 * init 方法用这个
	 */
	@Override
	public Config findById(Integer id) {
		return configDao.findId(id);
	}
	
	

	@Override
	public void init_properties() {
		MyUtil.uploadPath = uploadPath;
	}
	
	
	
}
