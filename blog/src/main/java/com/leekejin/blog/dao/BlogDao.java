package com.leekejin.blog.dao;

import com.leekejin.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
    Blog getBlogById(Long id);
    Blog getDetailedBlog(Long id);
    List<Blog> getAllBlogs();
    List<Blog> getByTypeId(Long typeId);
    List<Blog> getByTagId(Long tagId);
    List<Blog> getByTypeName(String typeName);
    List<Blog> getByTagName(String tagName);
    List<Blog> getIndexBlog();  // 主页博客展示
    List<Blog> getRecommendBlog();
    List<Blog> getSearchBlog(String query);  // 全局搜索博客

    List<Blog> searchAllBlog(Blog blog); // 搜索博客

    List<Blog> findByYear(String year);
    int saveBlog(Blog blog);
    int updateBlog(Blog blog);
    int saveBlogAndTag(Long bid, Long tid);
    int deleteBlog(Long id);
}
