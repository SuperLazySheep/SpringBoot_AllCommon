package com.kiki.quartz3.controller;

import com.kiki.quartz3.entity.User;
import com.kiki.quartz3.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoTest1 {
    @Autowired
    private UserDao userDao;

    public void save(){
        User user = userDao.findById(1);
        System.out.println(user.getId() + "----------"+ user.getName());
        System.out.println("------user-----add-----");
    }
}
