package com.demo.dao;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by james on 2018/6/11.
 */
public interface UserMapper {

    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
