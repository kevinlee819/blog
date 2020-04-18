package com.leekejin.blog.service.impl;

import com.leekejin.blog.dao.UserDao;
import com.leekejin.blog.pojo.User;
import com.leekejin.blog.service.UserService;
import com.leekejin.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
