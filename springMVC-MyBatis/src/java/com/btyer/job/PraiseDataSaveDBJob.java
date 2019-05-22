package com.btyer.job;

import com.btyer.model.Mood;
import com.btyer.model.UserMoodPraiseRel;
import com.btyer.service.MoodService;
import com.btyer.service.UserMoodPraiseRelService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 描述：定时器
 *
 * @author btyer
 * @create 2019/4/13 10:23
 */

@Component
@Configurable
@EnableScheduling
public class PraiseDataSaveDBJob {

    @Scheduled(cron = "*/60 * *  * * * ")
    public void savePraiseDataToDB(){
        System.out.println("running ...");
    }

    @Resource
    private RedisTemplate redisTemplate;
    private static final String PRAISE_HASH_KEY="springmvc_mybatis.com.btyer.mood.id.list.key";

    @Resource(name="UserMoodPraiseRelServiceImpl")
    private UserMoodPraiseRelService userMoodPraiseRelService;

    @Resource(name="MoodServiceImpl")
    private MoodService moodService;

    @Scheduled(cron = "0 0 12  * * ? ")
    public void savePraiseDataToDB2(){
        //将redis中的数据存放到数据库中，并清除redis中的数据
        //1、获取在redis缓存中所有被点赞说说的id
        Set<String> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
        if (CollectionUtils.isEmpty(moods)){
            return;
        }

        for (String moodId : moods) {
            if (redisTemplate.opsForSet().members(moodId) == null){
                continue;
            }else {
                //2、从redis缓存中，通过说说id获取所有点赞的用户id列表

                Set<String> userIds = redisTemplate.opsForSet().members(moodId);

                if (CollectionUtils.isEmpty(userIds)){
                    continue;
                }else {
                    // 3、循环保存mood_id和user_id的关联关系到MySQL数据库
                    for (String userId : userIds) {
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setMoodId(moodId);
                        userMoodPraiseRel.setUserId(userId);
                        //保存说说与用户的关联关系
                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }
                    Mood mood = moodService.findById(moodId);

                    //4、更新说说点赞数量
                    mood.setPraiseNum(mood.getPraiseNum()+redisTemplate.opsForSet().size(moodId).intValue());
                    moodService.update(mood);

                    //5、清除redis缓存中的数据
                    redisTemplate.delete(moodId);
                }
            }
        }
        //6、清除redis缓存中的数据
        redisTemplate.delete(PRAISE_HASH_KEY);
    }
}
