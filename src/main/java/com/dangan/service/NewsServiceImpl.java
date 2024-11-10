package com.dangan.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dangan.dao.NewsDao;
import com.dangan.entity.News;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	
	@Resource
	private NewsDao newsDao  ;
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public News repalce(News curr,News origin){
		
		if(curr.getTitle() ==null){
			curr.setTitle(origin.getTitle());
		}
		if(curr.getContent() ==null){
			curr.setContent(origin.getContent());
		}
		if(curr.getUser() ==null){
			curr.setUser(origin.getUser());
		}
		if(curr.getCreateDateTime()==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		
		return curr;
	}
	
	
	
	@Override
	public void update(News news) {
		News origin = newsDao.findId(news.getId());
		news = repalce(news, origin);
		newsDao.save(news);
	}
	
	
	
	
}
