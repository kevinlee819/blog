package com.leekejin.blog.service;

import com.leekejin.blog.pojo.User;


public interface UserService {
    User checkUser(String username, String password);
}
