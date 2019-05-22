package com.btyer.service.impl;

import com.btyer.dao.MoodDao;
import com.btyer.dao.UserDao;
import com.btyer.dao.UserMoodPraiseRelDao;
import com.btyer.dto.MoodDTO;
import com.btyer.model.Mood;
import com.btyer.model.User;
import com.btyer.model.UserMoodPraiseRel;
import com.btyer.mq.MoodProducer;
import com.btyer.service.MoodService;
import com.btyer.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述：说说服务类
 * @author btyer
 * @date 2019/4/3
 */
@Service("MoodServiceImpl")
public class MoodServiceImpl implements MoodService {

    @Resource
    private MoodDao moodDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    @Resource
    private RedisTemplate redisTemplate;


    @Resource(name = "moodProducer")
    MoodProducer moodProducer;



    // KEY的命名规范：项目名称+模块名称+具体内容
    private static final String PRAISE_HASH_KEY = "springmvc_mybatis.com.btyer.mood.id.list.key";


    //队列
    private static Destination destination = new ActiveMQQueue("btyer.queue.high.concurrency.praise");

    public List<MoodDTO> findAll() {
        List<Mood> moodList = moodDao.findAll();
        return converModel2DTO(moodList);
    }

    public boolean praiseMood(String userId, String moodId) {
        //保存关联关系
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setUserId(userId);
        userMoodPraiseRel.setMoodId(moodId);
        userMoodPraiseRelDao.save(userMoodPraiseRel);
        //更新说说的点赞数量
        Mood mood = this.findById(moodId);
        mood.setPraiseNum(mood.getPraiseNum() + 1);
        this.update(mood);

        return Boolean.TRUE;
    }

    public boolean update(Mood mood) {
        return moodDao.update(mood);
    }

    public Mood findById(String id) {
        return moodDao.findById(id);
    }


    private List<MoodDTO> converModel2DTO(List<Mood> moodList){
        if(CollectionUtils.isEmpty(moodList)) return Collections.EMPTY_LIST;
        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();
        for(Mood mood:moodList){
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setContent(mood.getContent());
            moodDTO.setPraiseNum(mood.getPraiseNum());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setUserId(mood.getUserId());
            moodDTOList.add(moodDTO);
            //设置用户信息
            User user = userDao.find(mood.getUserId());
            moodDTO.setUserName(user.getName());
            moodDTO.setUserAccount(user.getAccount());
        }
        return moodDTOList;
    }



    @Override
    public boolean praiseMoodForRedis(String userId, String moodId) {


        //修改为异步处理方式
        MoodDTO moodDTO = new MoodDTO();
        moodDTO.setUserId(userId);
        moodDTO.setId(moodId);

        //发送消息
        moodProducer.sendMessage(destination,moodDTO);

        //1、存放到set集合中

    //    redisTemplate.opsForSet().add(PRAISE_HASH_KEY,moodId);

        //2、存放到set集合
     //   redisTemplate.opsForSet().add(moodId,userId);
        return false;
    }




    @Resource(name="UserServiceImpl")
    private UserService userService;

    @Override
    public List<MoodDTO> findAllForRedis() {
        List<Mood> moodList = moodDao.findAll();
        if(CollectionUtils.isEmpty(moodList)){
            return Collections.EMPTY_LIST;
        }
        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();

        for (Mood mood : moodList) {
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setUserId(mood.getUserId());
            // right = 总点赞数：数据库的点赞数+redis的点赞数
            moodDTO.setPraiseNum(mood.getPraiseNum()+redisTemplate.opsForSet().size(mood.getId()).intValue());

            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setContent(mood.getContent());

            //通过userId查询用户
            User user = userService.find(mood.getUserId());
            //用户名‘
            moodDTO.setUserName(user.getName());
            //账户
            moodDTO.setUserAccount(user.getAccount());
            moodDTOList.add(moodDTO);

        }
        return moodDTOList;
    }
}
