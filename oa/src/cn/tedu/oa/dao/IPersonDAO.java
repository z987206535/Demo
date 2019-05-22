package cn.tedu.oa.dao;

import java.util.List;

import cn.tedu.oa.domain.Person;

public interface IPersonDAO {
	  public void save(Person person);
	  public void delete(int id);
	  public Person findById(int id);
	  //分页
	  public List<Person> findPersonByPageNo(int pageNo);
	  //该级别下多少页
	  public long findPersonTotalPage();

}
