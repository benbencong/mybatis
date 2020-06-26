package com.service.impl;

import com.dao.IUserDao;
import com.model.User;
import com.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService {
    /*
    @Resource装配顺序
　　1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
　　2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
　　3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
　　4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；
     */
    @Resource
    private IUserDao userDao;

    public User getUserById(int userId) {  
        // TODO Auto-generated method stub  
        return this.userDao.selectByPrimaryKey(userId);  
    }
    public int insertUser(User u){
        //System.out.println(u.getId());
        System.out.println(u.getAge());
        System.out.println(u.getUserName());

        return this.userDao.insert(u);
    }

    @Override
    public List<User> queryByName(String username){
        List<User> userList = userDao.queryByName(username);
        return userList;
    }

       @Override
    public List<User> getUserList(){
        List<User> userList = userDao.getUserList();
        return userList;
    }


    @Override
    public int deleteUser(int id){
        int a = userDao.deleteUser(id);
        return a;
    }



}  
