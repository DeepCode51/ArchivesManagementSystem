package com.dangan.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Administrator
 *
 *123456 =ba61ce8fa1e3725876e6363c76043c8d
 */
public class CryptographyUtil {

	public static void main(String[] args) throws Exception {
		System.out.println(md5("123456", "java"));
	}

	/**
	 * Md5加密
	 * 
	 * @param str
	 *            加密的内容
	 * @param salt
	 *            盐值
	 */
	public static String md5(String str, String salt) {
		return new Md5Hash(str, salt).toString();
	}


}
