package com.btyer.dao;

import com.btyer.model.Mood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Btyer
 * @create 2018/06/30
 **/
@Repository
public interface MoodDao {

    List<Mood> findAll();

    boolean update(@Param("mood") Mood mood);

    Mood findById(String id);
}
