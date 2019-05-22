package com.btyer.service.impl;

import com.btyer.dao.UserDao;
import com.btyer.dto.UserDTO;
import com.btyer.model.User;
import com.btyer.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 描述：用户服务类
 * @author Ay
 * @date 2018/1/6.
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public UserDTO find(String id) {
        User user =  userDao.find(id);
        return converModel2DTO(user);
    }


    private UserDTO converModel2DTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAccount(user.getAccount());
        userDTO.setName(user.getName());
        return userDTO;
    }
}
