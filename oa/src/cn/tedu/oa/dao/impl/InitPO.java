package cn.tedu.oa.dao.impl;

import java.sql.ResultSet;

public interface InitPO<T>{
  public T init(ResultSet rs);
}
