package com.demo.service.impl;

import com.demo.config.SpringContextUtil;
import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.event.QuotationOrderStatusEvent;
import com.demo.service.CommonService;
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

    @Autowired
    private CommonService commonService;


    @Transactional
    @Override
    public User getUserById(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);

        addUser(user);

        System.out.println("当前线程 : " + Thread.currentThread().getName());

        String orderNo = "D123456";
        QuotationOrderStatusEvent quotationOrderStatusEvent = new QuotationOrderStatusEvent(orderNo);
        quotationOrderStatusEvent.setOrderNo(orderNo);
        quotationOrderStatusEvent.setOldStatus(2);
        quotationOrderStatusEvent.setNewStatus(3);
        SpringContextUtil.getContext().publishEvent(quotationOrderStatusEvent);
        int i = 1/0;
        return user;
    }

    @Transactional
    @Override
    public boolean addUser(User record){
        userMapper.insertSelective(record);
        return true;
    }

    /**
     * ransaction marked as rollbackOnly
     * https://blog.csdn.net/f641385712/article/details/80445912
     */
    @Transactional
    @Override
    public void testTransactionMark() {
        User user = new User();
        user.setAge(22);
        try {
            commonService.commonAdd();
        } catch (Exception e) {
            System.out.println("异常了 e : " + e.getMessage());
        }
    }




}