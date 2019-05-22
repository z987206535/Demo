package cn.tedu.oa.dao;

import java.util.List;
import cn.tedu.oa.domain.Org;

public interface IOrgDAO extends BaseDAO{
  public void save(Org org);
  public void delete(int id);
  public void update(Org org);
  public Org findById(int id);
  public List<Org> findAll();
  //分页
  public List<Org> findOrgByPageNo(int pageNo,int pid);
  //该级别下多少页
  public long findOrgTotalPage(int pid);
  //查询父父机构id
  public int getPPid(int pid);
  //查询是否有子机构
  public boolean isChildren(int id);
  
}
