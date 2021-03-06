package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author james
 * @date 2018/6/11
 */
@RestController
@RequestMapping("/testboot")
public class TestBootController {

    @Autowired
    private UserService userService;

    @GetMapping("/getuser")
    public User getUser() {
        User user = userService.getUserById(1);
        return user;
    }

    @GetMapping("/test")
    @Transactional
    public Boolean testTransactionMark() {
        userService.testTransactionMark();
        return true;
    }

}
