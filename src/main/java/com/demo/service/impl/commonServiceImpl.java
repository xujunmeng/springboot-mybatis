package com.demo.service.impl;

import com.demo.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author james
 * @date 2019/10/15
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Transactional
    @Override
    public void commonAdd() {
        int i = 1/0;
    }
}
