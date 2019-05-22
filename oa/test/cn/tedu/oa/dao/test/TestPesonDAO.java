package cn.tedu.oa.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.tedu.oa.dao.IPersonDAO;
import cn.tedu.oa.dao.impl.PersonMySQLDaoImpl;
import junit.framework.TestCase;

public class TestPesonDAO extends TestCase{
  public void test(){
	  IPersonDAO dao = new PersonMySQLDaoImpl();
	  /*
	  for(int i=1;i<=10;i++){
	  Person p = new Person();
	  p.setAddr("111");
	  p.setAge("2000-10-10");//???
	  p.setDescr("111"+i);
	  p.setGender("女");
	  p.setJob("manager");
	  p.setName("tom"+i);
	  p.setOid(10+"");
	  p.setTel("111");
	  dao.save(p);
	  }
	  */
	  //p = dao.findById(1);
	  //assertEquals("女", p.getGender());
	  //10:00---10:10
	  //List<Person> rows = dao.findPersonByPageNo(2);
	  //assertEquals(2, rows.size());
	  //assertEquals(6, dao.findPersonTotalPage());
	  //System.out.println(rows);
	  
	  
  
//SimpleDateFormat sdf = 
//	        new SimpleDateFormat("yyyy-MM-dd");
//java.util.Date now = sdf.parse("2000-10-10");
//  rs.setDate(1,new java.sql.Date(now.getTime())); 

	  
//java一组类格式化
//Thu Oct 27 10:19:16 CST 2016
try {
	System.out.println(new Date());
	//格化日期对象
	SimpleDateFormat sdf 
	= new SimpleDateFormat("yyyy-MM-dd");
	String input = sdf.format(new Date());
	System.out.println(input);
	input = "2000-10-10";
	Date now = sdf.parse(input);
	
	//java.util.Date   年月日时分秒  
	//java.sql.Date    年月日
	
	//stmt.setDate(1,);//java.sql.Date
	java.util.Date now1 = new java.util.Date();
	java.sql.Date now2 = new java.sql.Date(now1.getTime());
	now1 = new java.util.Date(now2.getTime());
	
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	  
	  
	  
	  
	  
  }
}
