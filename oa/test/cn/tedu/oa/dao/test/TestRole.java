package cn.tedu.oa.dao.test;

import cn.tedu.oa.dao.IRoleDAO;
import cn.tedu.oa.dao.impl.RoleMySQLDAOImpl;
import cn.tedu.oa.domain.Role;
import cn.tedu.oa.service.IRoleService;
import cn.tedu.oa.service.impl.RoleServiceImpl;
import junit.framework.TestCase;

public class TestRole extends TestCase{
    public void test(){
    	IRoleService sf = new RoleServiceImpl();
    	//sf.save(new Role(1,"系统管理员"));
    	//sf.save(new Role(1,"普通员工"));
    	//sf.save(new Role(1,"部门领导"));
    	//sf.save(new Role(1,"档案管理员"));
    	//sf.save(new Role(1,"吃瓜群众"));
    	//assertEquals(5,sf.findByPage(1).size());
    	//assertEquals(1,sf.findTotalPage());
    	RoleMySQLDAOImpl dao = new RoleMySQLDAOImpl();
    	
    	//dao.setAcl(1,8,"U",1);//添加
    	//dao.setAcl(1,8,"C",1);//添加
    	//dao.setAcl(1,8,"R",1);//添加
    	//dao.setAcl(1,8,"D",1);//添加
    	dao.setAcl(1, 8, "c", 0);
    	
    	//assertEquals(true, dao.isMidRid(1, 8));
    	//assertEquals(false, dao.isMidRid(1, 1));
    	
    	
    }
}
