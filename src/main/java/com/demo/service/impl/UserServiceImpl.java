package com.demo.service.impl;

import com.demo.config.SpringContextUtil;
import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.event.QuotationOrderStatusEvent;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author james
 * @date 2018/6/11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Transactional
    @Override
    public User getUserById(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);

        addUser(user);

        String orderNo = "D123456";
        QuotationOrderStatusEvent quotationOrderStatusEvent = new QuotationOrderStatusEvent(orderNo);
        quotationOrderStatusEvent.setOrderNo(orderNo);
        quotationOrderStatusEvent.setOldStatus(2);
        quotationOrderStatusEvent.setNewStatus(3);
        SpringContextUtil.getContext().publishEvent(quotationOrderStatusEvent);
        System.out.println("当前线程 : " + Thread.currentThread().getName());
        return user;
    }

    @Transactional
    @Override
    public boolean addUser(User record){
        userMapper.insertSelective(record);
        return true;
    }

}