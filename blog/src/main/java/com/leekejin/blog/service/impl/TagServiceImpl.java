package com.leekejin.blog.service.impl;

import com.leekejin.blog.dao.TagDao;
import com.leekejin.blog.pojo.Tag;
import com.leekejin.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Override
    public List<Tag> getTagByString(String text) {    //从tagIds字符串中获取id，根据id获取tag集合
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long long1 : longs) {
            tags.add(tagDao.getTag(long1));
        }
        return tags;
    };

    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
