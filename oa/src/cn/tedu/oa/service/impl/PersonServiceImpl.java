package cn.tedu.oa.service.impl;

import java.util.List;

import cn.tedu.oa.dao.IPersonDAO;
import cn.tedu.oa.dao.impl.PersonMySQLDaoImpl;
import cn.tedu.oa.domain.Person;
import cn.tedu.oa.service.IPersonService;

public class PersonServiceImpl implements IPersonService {
    IPersonDAO dao = new PersonMySQLDaoImpl();
	@Override
	public List<Person> findPersonAllByPageNo(int pageNo) {
		return dao.findPersonByPageNo(pageNo);
	}
	@Override
	public long findPersonTotalPage() {
		return dao.findPersonTotalPage();
	}
	@Override
	public void delPersonById(int id) {
		if(dao.findById(id)==null){
			return;
		}
		dao.delete(id);
	}
	@Override  //15:52---16:02
	public void addPerson(Person p) {
		dao.save(p);
	}

}
