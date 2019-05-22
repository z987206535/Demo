package cn.tedu.oa.dao.test;

import cn.tedu.oa.service.IOrgService;
import cn.tedu.oa.service.impl.OrgServiceImpl;
import junit.framework.TestCase;

public class TestOrgService extends TestCase{
	IOrgService s = new OrgServiceImpl();
    public void test1(){
    	assertEquals(false,s.deleteOrgById(1));
    	assertEquals(true,s.deleteOrgById(24));
    }
}
