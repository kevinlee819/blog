package com.leekejin.blog.service.impl;

import com.leekejin.blog.dao.BlogDao;
import com.leekejin.blog.dao.TagDao;
import com.leekejin.blog.exceptions.NotFoundException;
import com.leekejin.blog.pojo.Blog;
import com.leekejin.blog.pojo.Tag;
import com.leekejin.blog.service.BlogService;
import com.leekejin.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private TagDao tagDao;


    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Transactional
    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        blogDao.updateViews(blog.getId());
        return blog;
    }

    @Transactional
    @Override
    public List<Blog> getAllBlogs() {
        return blogDao.getAllBlogs();
    }

    @Transactional
    @Override
    public List<Blog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Transactional
    @Override
    public List<Blog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Transactional
    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogDao.getRecommendBlog();
    }

    @Transactional
    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Transactional
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Transactional
    @Override
    public int countBlog() {
        return blogDao.getAllBlogs().size();
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        for (Tag tag : tags) {
            blogDao.saveBlogAndTag(tag.getId(), id);
        }
        return 1;
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        List<Tag> originTags = tagDao.getTagsByBlogId(blog.getId());
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中,将不存在的删除,新增的添加
        List<Tag> newTags = blog.getTags();
        for (Tag tag : newTags) {
            if (originTags.contains(tag)) {
                originTags.remove(tag);
            } else {
                blogDao.saveBlogAndTag(tag.getId(),blog.getId());
            }
        }
        for (Tag tag: originTags) {
            blogDao.deleteBlogAndTag(tag.getId(),blog.getId());
        }
        return blogDao.updateBlog(blog);
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

    @Transactional
    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogDao.searchAllBlog(blog);
    }

}
