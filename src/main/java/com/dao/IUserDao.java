package com.dao;

import com.model.User;

import java.util.List;


public interface IUserDao {

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    List<User> queryByName(String username);

    List<User> getUserList();

    int deleteUser(int id);
}