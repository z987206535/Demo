package com.btyer.test;



import com.btyer.dao.MoodDao;
import com.btyer.dto.MoodDTO;
import com.btyer.model.Mood;
import com.btyer.service.MoodService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;


public class UserDaoTest extends  BaseJunit4Test {

    @Resource
    private MoodService moodService;

    @Resource
    private MoodDao moodDao;


    @Test
    public void MoodServiceTest(){
       List<MoodDTO> moodList =  moodService.findAll();

       System.out.println("说说："+moodList.size());
    }
    @Test
    public void MoodTest(){
       List<Mood> moodList =  moodDao.findAll();
       System.out.println("说说："+ moodList.toString());
    }





}

