package com.demo.event;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author james
 * @date 2019/10/12
 */
@Component
public class QuotationOrderStatusEventHandler implements ApplicationListener<QuotationOrderStatusEvent>{

    @Autowired
    private UserService userService;

    @Transactional
    @Async
    @Override
    public void onApplicationEvent(QuotationOrderStatusEvent event) {
        System.out.println("当前线程 : " + Thread.currentThread().getName() + " 事件体 : " + event);

        User user = new User();
        user.setUserName("32wfsdaf");
        userService.addUser(user);
        int i = 1/0;
    }
}
