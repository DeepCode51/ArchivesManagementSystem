package com.dangan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


public class MyUtil {
	
	//上传文件路径  从配置文件 初始化。
	public static String uploadPath = "";
	
	
	public static void main(String s[]){
		BigDecimal mal = new BigDecimal(1);
		BigDecimal zero = new BigDecimal(0);
		
		if(mal.compareTo(zero)>0){
			System.out.println("大于0");
		}else {
			System.out.println("小于0");
		}
		
	}
	
	
	
	/**
	 * 取得用户ip
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for"); 
		 // System.out.println("x-forwarded-for ip: " + ip);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
	            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
	            if( ip.indexOf(",")!=-1 ){
	                ip = ip.split(",")[0];
	            }
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	            //  System.out.println("Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	            //  System.out.println("WL-Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	            //  System.out.println("HTTP_CLIENT_IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	            // System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("X-Real-IP");  
	           // System.out.println("X-Real-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	            //  System.out.println("getRemoteAddr ip: " + ip);
	        } 
	        //System.out.println("获取客户端ip: " + ip);
	        return ip;  
		/*String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;*/
	}

	/**
	 * 把传来的string ids 变成 List<Integer> ids = new ArrayList<Integer>() 返回
	 * 因为mybatis查范围,要用的迭代
	 */
	public static List<Integer> Str_ids_To_ListInteger_ids(String ids) {
		List<Integer> ListInteger_ids = new ArrayList<Integer>();
		String[] arr = ids.split(",");
		for (String i : arr) {
			// 验证是不是数字
			if (i.matches("\\d+")) {
				ListInteger_ids.add(Integer.parseInt(i));
			}
		}
		return ListInteger_ids;
	}

	/**
	 * 这个方法是拿 复选框 时用的 判断id在不在ids之内。如果在返回true
	 */
	public static boolean existStrArr(String id, String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(id)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 以arr1为 基准 把arr2的内容从arr中过虑掉。删除 返回新的arr
	 */
	private static String[] arrContrast(String[] arr1, String[] arr2) {
		List<String> list = new LinkedList<String>();
		for (String str : arr1) { // 处理第一个数组,list里面的值为1,2,3,4
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : arr2) { // 如果第二个数组存在和第一个数组相同的值，就删除
			if (list.contains(str)) {
				list.remove(str);
			}
		}
		String[] result = {}; // 创建空数组
		return list.toArray(result); // List to Array
	}

	public static boolean makeDirs(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			return false;
		}
		File folder = new File(filePath);
		if (folder.exists()) {// IsDirectory( ) 判断文件夹是否存在
			return true;
		} else {
			return folder.mkdirs();
		}
	}
	
	
	/**
	 * 将long转成date 这个long必须是13位
	 * 如果是10位的话需要*1000
	 * @param times
	 * @param formatDate
	 * @return
	 */
	public static Date longToDate(long times, String formatDate) {
		// "yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		Long time = new Long(times);
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * @param num      是要补0的数字
	 * @param count    是总位数    数字不够总位数不够就用0代替
	 */
	public static String bu0(Integer num,int count){
	    String str = String.format("%0"+count+"d", num);   
		return str.trim();
	}
	
	
	/**
	 * 
	 * 360浏览器 =AppleWebKit
	 * 火狐=Firefox
	 * 搜狗=AppleWebKit
	 * qq浏览器=AppleWebKit
	 * 
	 * @return
	 */
	public static boolean checkUserAgent(String UserAgent){
		boolean falg = false;
		if(UserAgent.indexOf("AppleWebKit")!=-1){
			falg = true;
		}
		if(UserAgent.indexOf("Firefox")!=-1){
			falg = true;
		}
		return falg;
	}
	
	
	/**
	 * 返回 所有的月份，01到12
	 * @return
	 */
	public static List<String> getMonths(){
		List<String> months = new ArrayList<String>();
		for(int i=1;i<=12;i++){
			months.add(bu0(i, 2)+"");
		}
		return months;
	}
	
	/**
	 * 返回年份 上下各 错10年
	 * @param year
	 * @return
	 */
	public static List<String> getYears(int year){
		List<String> years = new ArrayList<String>();
		year = year -10 ;
		for(int i=1;i<=20;i++){
			year = year+1;
			years.add(year+"");
		}
		return years;
	}
	
}
