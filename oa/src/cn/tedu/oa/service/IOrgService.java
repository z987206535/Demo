package cn.tedu.oa.service;

import java.util.List;

import cn.tedu.oa.domain.Org;

public interface IOrgService {
  //1:获取分页内容方法	
  public List<Org> findOrgAllByPageNo(int pageNo,int pid);
  //2:获取总页数方法
  public long findOrgTotalPage(int pid);
  //3:完成返回
  public int getPPid(int pid);
  //4:删除机构
  public boolean deleteOrgById(int id);
  //5:添加机构
  public void addOrg(Org org);
  //6:查询所有机权
  public List<Org> findAll();
}
