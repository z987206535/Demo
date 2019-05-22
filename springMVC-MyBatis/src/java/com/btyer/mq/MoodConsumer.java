package com.btyer.mq;

import com.btyer.dto.MoodDTO;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;



/**
 * 描述：
 *
 * @author btyer
 * @create 2019/4/17 9:47
 */
@Component("moodConsumer")
public class MoodConsumer  implements MessageListener {

    private static final String PRAISE_HASH_KEY="springmvc_mybatis.com.btyer.mood.id.list.key";

   Logger log = Logger.getLogger(this.getClass());

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void onMessage(Message message) {
        try {
            // 获取message中的说说实体
            MoodDTO mood = (MoodDTO) ((ActiveMQObjectMessage)message).getObject();
            // 1、存放到set中
            redisTemplate.opsForSet().add(PRAISE_HASH_KEY,mood.getId());
            // 2、存放到set中
            redisTemplate.opsForSet().add(mood.getId(),mood.getUserId());

            log.info("消费者---- >>>用户id: "+ mood.getUserId()+ " 给说说id:"+
                    mood.getId() + " 点赞");

        }catch (Exception e){
           System.out.println(e);
        }
    }
}
