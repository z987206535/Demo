package com.lzjtedu.txu.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
	//定义静态变量 projectNamemd5
	public static String projectNamemd5 = "tongxunlu";	

	  //1:创建对象{处理属性文件} Properties
	  private static Properties props = new Properties();
	  //2:静态块,加载属性文件
	  static{
			InputStream ips = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
			try{
				props.load(ips);
			}catch(Exception e){
				e.printStackTrace();
			}
			projectNamemd5 = MD5Utils.md5("tongxunlu");
	  }
		//3:添加静态方法 getValue
	public static String getVal(String key){
		return props.getProperty(key);
	}
	
	
	
	public static void main(String[] args) {
		String key = ConfigUtils.getVal("pagesize");
		System.out.println(key);
	}
	
	
}

