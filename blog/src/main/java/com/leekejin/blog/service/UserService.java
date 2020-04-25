package com.leekejin.blog.service;

import com.leekejin.blog.pojo.User;

import java.util.Date;


public interface UserService {
    User checkUser(String username);

    int createUser(String nickname, String email, Integer type, String avatar);

    User searchUser(String email);
}
