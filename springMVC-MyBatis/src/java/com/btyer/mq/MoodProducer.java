package com.btyer.mq;

import com.btyer.dto.MoodDTO;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 描述：
 * @author btyer
 * @create 2019/4/17 9:34
 */
@Component("moodProducer")
public class MoodProducer {

    @Resource
    private JmsTemplate jmsTemplate;

    Logger log = Logger.getLogger(this.getClass());

    public void sendMessage(Destination destination , final MoodDTO mood)  {
        log.info("生产者--->>> 用户id: "+mood.getUserId() + "给说说id "+
                mood.getId()+" 点赞");
        // mood 实体需要实现Serializable序列化接口
        jmsTemplate.convertAndSend(destination,mood);
    }
}
