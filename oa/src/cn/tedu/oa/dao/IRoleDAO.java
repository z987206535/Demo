package cn.tedu.oa.dao;

import java.util.List;

import cn.tedu.oa.domain.Role;

public interface IRoleDAO {
  public void save(Role r);
  public void delete(int id);
  public Role findById(int id);
  public List<Role> findRoleByPageNo(int pageNo);
  public long  findRoleTotalPage();
  public void  setAcl(int rid,int mid,String opt,int val);
}
