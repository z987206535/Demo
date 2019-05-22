package cn.tedu.oa.service.impl;

import java.util.List;

import cn.tedu.oa.dao.IRoleDAO;
import cn.tedu.oa.dao.impl.RoleMySQLDAOImpl;
import cn.tedu.oa.domain.Role;
import cn.tedu.oa.service.IRoleService;

public class RoleServiceImpl implements IRoleService{
    IRoleDAO dao = new RoleMySQLDAOImpl();
	@Override
	public List<Role> findByPage(int pageNo) {
		return dao.findRoleByPageNo(pageNo);
	}

	@Override
	public long findTotalPage() {
		return dao.findRoleTotalPage();
	}

	@Override
	public void save(Role r) {
		dao.save(r);
	}

	@Override
	public void delete(int id) {
	}

}
