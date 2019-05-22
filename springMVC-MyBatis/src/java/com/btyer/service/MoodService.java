package com.btyer.service;

import com.btyer.dto.MoodDTO;
import com.btyer.model.Mood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：说说接口
 * @author btyer
 * @date 2019/4/3
 */

public interface MoodService {

    //传统查询
    List<MoodDTO> findAll();
    //传统点赞
    boolean praiseMood(String userId, String moodId);

    boolean update(@Param("mood") Mood mood);

    Mood findById(String id);

    boolean praiseMoodForRedis(String userId,String moodId);

    List<MoodDTO> findAllForRedis();

}
