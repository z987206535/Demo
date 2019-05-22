package com.btyer.dao;

import com.btyer.model.AyRole;
import org.springframework.stereotype.Repository;


/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/17 7:59
 */
@Repository
public interface AyRoleDao {

    AyRole findById(Integer id);
}
