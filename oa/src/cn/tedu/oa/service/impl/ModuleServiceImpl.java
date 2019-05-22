package cn.tedu.oa.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.tedu.oa.dao.impl.InitPO;
import cn.tedu.oa.dao.impl.ModuleMySQLImpl;
import cn.tedu.oa.domain.Module;
import cn.tedu.oa.service.IModuleService;

public class ModuleServiceImpl implements IModuleService {

	ModuleMySQLImpl dao = new ModuleMySQLImpl();
	 
	@Override
	public List<Module> findModuleAllByPageNo(int pageNo) {
		return dao.findByPageNo("t_module", " ", pageNo,  new InitPO<Module>(){
		public Module init(ResultSet rs) {
			Module m = new Module();
			try {
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setUrl(rs.getString("url"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return m;
		}
		   
	   });
		
	}

	@Override
	public long findModuleTotalPage() {
		return dao.findTotalPage("t_module", " ");
	}

	@Override
	public void delById(int id) {
	}

	@Override
	public void add(Module p) {
	}

	@Override
	public List<Module> findAll() {
		return dao.findAll();
	}

}
