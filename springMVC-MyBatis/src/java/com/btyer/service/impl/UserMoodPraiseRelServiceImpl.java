package com.btyer.service.impl;

import com.btyer.dao.UserMoodPraiseRelDao;
import com.btyer.model.UserMoodPraiseRel;
import com.btyer.service.UserMoodPraiseRelService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 描述：用户说说点赞关联服务类
 * @author Ay
 * @date  2018/1/6.
 */
@Service("UserMoodPraiseRelServiceImpl")
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService {

    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelDao.save(userMoodPraiseRel);
    }
}
