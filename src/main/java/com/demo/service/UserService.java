package com.demo.service;

import com.demo.entity.User;

/**
 * @author james
 * @date 2018/6/11
 */
public interface UserService {

    User getUserById(int userId);

    boolean addUser(User record);

}
