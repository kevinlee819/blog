package com.leekejin.blog.dao;

import com.leekejin.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTags();

    List<Tag> getBlogTags();  //博客标签

    List<Tag> getTagsByBlogId(Long bid);

    int updateTag(Tag tag);

    int deleteTag(Long id);
}
