package com.btyer.dao;

import com.btyer.model.AyUser;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/10 14:05
 */

@Repository
public interface AyUserDao {

    AyUser findById(String id);




}
