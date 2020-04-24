package com.leekejin.blog.dao;


import com.leekejin.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {
    //根据创建时间倒序来排
    List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId") Long blogId, @Param("CommentParentId") Long CommentParentId);

    //查询父级对象
    Comment findByParentCommentId(@Param("parentCommentId")Long parentcommentid);

    List<Comment> findByAncesterCommentId(@Param("ancesterCommentId")Long ancesterCommentId);

    Comment findById(Long id);

    //添加一个评论
    int saveComment(Comment comment);

    //单独删掉这一条评论
    int deleteOneComment(Long id);

    //将某条评论的回复评论删掉
    int deleteCommentByParentId(Long parentCommentId);
}
