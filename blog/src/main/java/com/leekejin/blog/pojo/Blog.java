package com.leekejin.blog.pojo;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Blog {


    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag; //原创或转载
    private Integer views;
    private boolean published; //是否发布
    private boolean recommend;
    private Date createTime;
    private Date updateTime;
    //这个属性用来在mybatis中进行连接查询的
    private Long typeId;
    private Long userId;
    //获取多个标签的id
    private String tagIds;
    private String description;

    private Type type;

    private User user;

    private List<Tag> tags = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();
}