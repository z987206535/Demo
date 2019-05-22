package cn.tedu.oa.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
  //1:创建对象{处理属性文件} Properties
  private static Properties props = 
    new Properties();
  //2:静态块,加载属性文件
  static{
	InputStream ips = 
	ConfigUtils.class.getClassLoader()
	.getResourceAsStream("config.properties");
	try{
	props.load(ips);
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  //3:添加静态方法 getValue
  public static String getVal(String key){
	   return props.getProperty(key);
  }
//  public static void main(String[] args) {
//	 System.out.println(getVal("msg"));
//  }
}
