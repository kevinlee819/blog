package com.leekejin.blog.service.impl;

import com.leekejin.blog.dao.TagDao;
import com.leekejin.blog.pojo.Tag;
import com.leekejin.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Transactional
    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Transactional
    @Override
    public List<Tag> getBlogTags() {
        return tagDao.getBlogTags();
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }
}
