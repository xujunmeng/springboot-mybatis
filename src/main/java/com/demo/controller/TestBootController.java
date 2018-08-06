package com.demo.controller;

import com.demo.entity.User;
import com.demo.model.ExceptionParamModel;
import com.demo.model.SelftException;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/exception")
    public User getException() {
        int i = 1/0;
        return null;
    }
}
