package com.btyer.dao;

import com.btyer.model.UserMoodPraiseRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 描述：用户说说点赞关联DAO
 * @author btyer
 * @create 2019/4/3
 **/
@Repository
public interface UserMoodPraiseRelDao {

    boolean save(@Param("userMoodPraiseRel") UserMoodPraiseRel userMoodPraiseRel);
}
