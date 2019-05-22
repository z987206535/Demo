package com.btyer.service;

import com.btyer.dto.UserDTO;

/**
 * 描述：用户服务接口
 * @author Ay
 * @date 2018/1/6.
 */
public interface UserService {

    UserDTO find(String id);

}
