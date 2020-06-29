package com.controller;



import com.model.Response;
import com.model.User;
import com.service.IUserService;

import com.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/user")
// /user/**
public class UserController {
    @Resource
    private IUserService userService;
    //UserServiceImpl userService=new UserServiceImpl();
   // @Autowired
    //private IUserService userService;
    //注册
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Response register(@RequestBody Map<String,String> person){
        String username = person.get("userName");
        String password = person.get("password");
        Integer age = Integer.valueOf(person.get("age"));
        System.out.println(username+ "  "+password+" "+age);

        if (username != null && password != null && age != null){
            List<User> user = userService.queryByName(username);
            User user1 = new User();
            user1.setAge(age);
            //user.setId(userId);
            user1.setPassword(password);
            user1.setUserName(username);
            if(user.size() == 0){
                int isadd = userService.insertUser(user1);
                if (isadd > 0){
                    return new Response("注册成功", 1, true);
                }else {
                    return new Response("注册失败", -1, false);
                }
            }else {
                return new Response("注册失败：用户名已存在",-1,false);
            }
        }else {
            return new Response("注册失败：用户名、密码、年纪不能为空",-1,false);
        }
    }

    //登录 ,method = RequestMethod.POST
    @RequestMapping(value = "login")
    public Response login(@RequestBody Map<String, String> person) {
        String username = person.get("userName");
        String password = person.get("password");
        if(username != null && password != null){
            List<User> user = userService.queryByName(username);
            if(user.size() == 0){
                return new Response("登录失败：用户名不存在", -1, false);
            }else {
                if (user.get(0).getPassword().equals(password)){
                    return new Response("登录成功", 1, true);
                }else {
                    return new Response("登录失败：密码错误", -1, false);
                }
            }
        }else {
            return new Response("登录失败：用户名或密码不能为空",-1,false);
        }
    }

    // /user/showUser?id=1
    @RequestMapping(value = "/showUser")
    public Response toIndex(HttpServletRequest request) {
        String username = request.getParameter("userName");
        System.out.println("userName:" + username);
        List<User> user = userService.queryByName(username);
        if(user.size() == 0){
            return new Response("用户名不存在", -1, false);
        }else {
        return new Response("成功",1,true,user);
        }
    }
    @RequestMapping(value = "/getUserList")
    public Response getUserList(){
        List<User> UserList = userService.getUserList();
        return new Response("查询成功",1,true,UserList);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Response deleteUser(@RequestBody User user){
        int id=user.getId();
        System.out.println(id);
        if(id!=0){
            int count = userService.deleteUser(id);
            System.out.println(count);
            if(count>0){
                Response response = new Response("删除成功",1,true);
                return response;
            }else {
                Response response = new Response("删除失败",-1,false);
                return response;
            }
        }else {
            Response response = new Response("删除失败，参数缺少",-1,false);
            return response;
        }

    }


}






