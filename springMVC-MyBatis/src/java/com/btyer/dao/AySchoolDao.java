package com.btyer.dao;

import com.btyer.model.AySchool;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/11 9:49
 */
@Repository
public interface AySchoolDao {
    AySchool findById(Integer id);
}
