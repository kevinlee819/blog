package com.leekejin.blog.service;

import com.leekejin.blog.pojo.Tag;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTags();

    List<Tag> getBlogTags();  //首页右侧展示tag对应的博客数量

    int updateTag(Tag tag);

    int deleteTag(Long id);
}
