package com.leekejin.blog.dao;

import com.leekejin.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface UserDao {
    User queryByUsernameAndPassword(@Param("username") String username);

    int createUser(@Param("nickname")String nickname, @Param("email") String email, @Param("type") Integer type, @Param("avatar") String avatar, @Param("createTime")Date createTime,  @Param("updateTime")Date updateTime);

    User searchUser(@Param("email") String email);

}
