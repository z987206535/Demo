package cn.tedu.oa.service;

import java.util.List;

import cn.tedu.oa.domain.Module;

public interface IModuleService {
	  //1:获取分页内容方法	
	  public List<Module> findModuleAllByPageNo(int pageNo);
	  //2:获取总页数方法
	  public long findModuleTotalPage();
	  //3:删除模块
	  public void delById(int id);
	  //4:添加模块
	  public void add(Module p);
	  //5:查询所有模块
	  public List<Module> findAll();
}
