package com.dangan.util;

public class BrowserUtil {
	
	public static void main(String[] args) {
	}
	
	/**
	 * 检测浏览器   是不是 极速 模式
	 * 360浏览器 =AppleWebKit
	 * 火狐=Firefox
	 * 搜狗=AppleWebKit
	 * qq浏览器=AppleWebKit
	 * 极速内核（webkit内核），兼容内核（trident内核）
	 * @return true极速模式    false 兼容模式
	 */
	public static boolean checkUserAgent(String UserAgent){
		boolean falg = true;
		UserAgent = UserAgent.toLowerCase();
		if(UserAgent.indexOf("trident")!=-1){
			falg = false;
		}
		return falg;
	}
	
	
	/**
	 * 微信 浏览器字符串
	 * Mozilla/5.0 (Linux; Android 7.1.1; MI MAX 2 Build/NMF26F; wv) 
	 * AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 
	 * Mobile MQQBrowser/6.2 TBS/043632 Safari/537.36 MicroMessenger/6.5.23.1180 
	 * NetType/WIFI Language/zh_CN
	 * 
	 * 
	 * 微信浏览器 判断  MicroMessenger
	 * 返回 true   就是手机浏览器   
	 * 返回 false  就是电脑
	 * @return
	 */
	public static boolean checkPhone(String UserAgent){
		return false;
	}
	
	
	
	
	
}
