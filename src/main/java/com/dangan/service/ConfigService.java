package com.dangan.service;

import com.dangan.entity.Config;

public interface ConfigService {
	
	public void update(Config config);
	
	/**
	 * init 方法用这个
	 */
	public Config findById(Integer id);
	
	public void init_properties();
}
