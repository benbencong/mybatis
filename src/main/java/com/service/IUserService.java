package com.service;

import com.model.User;

import java.util.List;


public interface IUserService {  
    public User getUserById(int userId);
    public int insertUser(User u);
    public List<User> queryByName(String username);
    public List<User> getUserList();
    public int deleteUser(int id);

}  