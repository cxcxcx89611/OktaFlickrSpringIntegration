package com.project.dao;

import com.project.pojo.User;
/*Pojo actions
* Use Mybatis framework to implement this interface please refer to UserMapper.xml
 * */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
