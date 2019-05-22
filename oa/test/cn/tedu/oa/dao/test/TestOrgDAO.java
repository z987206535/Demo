package cn.tedu.oa.dao.test;

import java.util.List;

import cn.tedu.oa.dao.IOrgDAO;
import cn.tedu.oa.dao.impl.OrgMySQLDaoImpl;
import cn.tedu.oa.domain.Org;
import junit.framework.TestCase;

public class TestOrgDAO extends TestCase{
    public void testDAO(){
    	IOrgDAO dao = new OrgMySQLDaoImpl();
    	//1:断言 相等
    	//assertEquals(null,dao.findById(100));
    	//2:断言 不为空
    	//assertNotNull(dao.findById(1));
    	//dao.delete(1);
    	//3:断言 相等
    	//assertEquals(null,dao.findById(1));
    	
    	//Org org = dao.findById(2);
    	//assertEquals("上海公司", org.getName());
    	//assertEquals(0, org.getPid());
    	//assertEquals("", org.getPname());
    	//System.out.println(org);
    	//long count = dao.findOrgTotalPage(1);
    	//assertEquals(5, count);
    	//List<Org> rows = dao.findOrgByPageNo(1, 1);
    	//assertEquals(2, rows.size());
    	//System.out.println(rows);
//    	
//    	for(int i=1;i<=11;i++){
//    		Org org = new Org();
//    		org.setName("广东分公司"+i);
//    		org.setPid(0);
//    		org.setPname("");
//    		org.setSn(10+i+"");
//    		org.setDescr("顶级公司");
//    		dao.save(org);
//    	}
    	//long count = dao.findOrgTotalPage(0);
    	//assertEquals(7, count);
    	//List<Org> rows = dao.findOrgByPageNo(2, 0);
    	//assertEquals(2, rows.size());
    	//System.out.println(rows);
    	
    	//assertEquals(0, dao.getPPid(1));
    	//assertEquals(0, dao.getPPid(0));
    	//assertEquals(true, dao.isChildren(1));
    	//assertEquals(false, dao.isChildren(2));
    	
    	dao.save(new Org(0,"ccc","","",0,""));
    	assertEquals("27", dao.findById(27).getSn());
    	dao.save(new Org(0,"海淀区子公司","","",1,""));
    	assertEquals("1_28", dao.findById(28).getSn());
    	assertEquals("北京分公司", dao.findById(28).getPname());
    	
          }
}
