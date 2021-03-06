package com.leekejin.blog.service.impl;

import com.leekejin.blog.dao.UserDao;
import com.leekejin.blog.pojo.User;
import com.leekejin.blog.service.UserService;
import com.leekejin.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User checkUser(String username) {
        User user = userDao.queryByUsernameAndPassword(username);
        return user;
    }
    //MD5Utils.code(password)

    @Transactional
    @Override
    public int createUser(String nickname, String email, Integer type, String avatar){
        User existUser = searchUser(email);
        if (existUser != null){
            return 0;
        }
        Date createTime = new Date();
        Date updateTime = new Date();
        return userDao.createUser(nickname, email,type,avatar,createTime,updateTime);
    }

    @Transactional
    @Override
    public User searchUser(String email){
        return userDao.searchUser(email);
    }

}
