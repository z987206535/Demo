package cn.tedu.oa.service;

import java.util.List;

import cn.tedu.oa.domain.Role;

public interface IRoleService {
   public List<Role> findByPage(int pageNo);
   public long findTotalPage();
   public void save(Role r);
   public void delete(int id);
}
