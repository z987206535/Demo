package cn.tedu.oa.dao.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.tedu.oa.dao.impl.InitPO;
import cn.tedu.oa.dao.impl.ModuleMySQLImpl;
import cn.tedu.oa.domain.Module;
import cn.tedu.oa.service.IModuleService;
import cn.tedu.oa.service.impl.ModuleServiceImpl;
import junit.framework.TestCase;

public class TestModule extends TestCase{
   public void test1(){
//	   ModuleMySQLImpl dao = new ModuleMySQLImpl();
//	   List<Module>  rows = dao.findByPageNo("t_module", " ", 1, new InitPO<Module>(){
//		public Module init(ResultSet rs) {
//			Module m = new Module();
//			try {
//				m.setId(rs.getInt("id"));
//				m.setName(rs.getString("name"));
//				m.setUrl(rs.getString("url"));
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return m;
//		}
//		   
//	   });
//	   assertEquals(2, dao.findTotalPage("t_module", " "));
//	   assertEquals(5, rows.size());
//	   System.out.println(rows); 
	   
	   
	   //IModuleService sf = new ModuleServiceImpl();
	   //assertEquals(5, sf.findModuleAllByPageNo(1).size());
	   //assertEquals(2, sf.findModuleTotalPage());
	   //assertEquals(1, sf.findModuleAllByPageNo(2).size());
	   //assertEquals("aa7",sf.findModuleAllByPageNo(2).get(0).getName());
	   
//	   ModuleMySQLImpl dao = new ModuleMySQLImpl();
//	   Module m = new Module(0,"机构管理","org");
//	   dao.save(m);
//	   m = new Module(0,"人员管理","person");
//	   dao.save(m);
//	   m = new Module(0,"模块管理","module");
//	   dao.save(m);
//	   m = new Module(0,"角色管理","role");
//	   dao.save(m);
//	   m = new Module(0,"用户管理","user");
//	   dao.save(m);
       //IModuleService ms = new ModuleServiceImpl();
       //List<Module> rows = ms.findAll();
       //assertEquals(5, rows.size());
       //assertEquals("机构管理", rows.get(0).getName());
 
   }
}
