package com.demo.model;

import com.aihuishou.common.exception.ServiceException;

/**
 * @author james
 * @date 2018/8/6
 */
public class SelftException extends ServiceException {


    public SelftException(Integer responseCode) {
        super(responseCode);
    }
}
