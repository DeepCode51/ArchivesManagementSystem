
package com.dangan.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dangan.util.MyUtil;


/**
 * 图片绝对地址与虚拟地址映射
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
     
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
    //文件磁盘图片url 映射
    //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
    registry.addResourceHandler("/upload_load/**").addResourceLocations("file:"+MyUtil.uploadPath);
    //registry.addResourceHandler("/userImage/**").addResourceLocations("file:D:\\userImages\\");
  }
  
}