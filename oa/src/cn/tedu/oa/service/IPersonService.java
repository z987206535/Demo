package cn.tedu.oa.service;

import java.util.List;

import cn.tedu.oa.domain.Person;

public interface IPersonService {
	  //1:获取分页内容方法	
	  public List<Person> findPersonAllByPageNo(int pageNo);
	  //2:获取总页数方法
	  public long findPersonTotalPage();
	  //3:删除人员
	  public void delPersonById(int id);
	  //4:添加人员
	  public void addPerson(Person p);
}
