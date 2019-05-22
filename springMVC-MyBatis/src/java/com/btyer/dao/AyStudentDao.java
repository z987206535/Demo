package com.btyer.dao;

import com.btyer.model.AyStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/11 9:48
 */
@Repository
public interface AyStudentDao {
    List<AyStudent> findBySchoolId(Integer id);
}
