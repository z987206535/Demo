package cn.tedu.oa.dao;

import java.util.List;

import cn.tedu.oa.domain.Module;

public interface IModule {
  public void save(Module m);
  public void delete(int id);
  public Module findById(int id);
  public List<Module> findAll();
  
}
