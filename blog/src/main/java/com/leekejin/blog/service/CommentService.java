package com.leekejin.blog.service;


import com.leekejin.blog.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

    List<Comment> listCommentByBlogId(Long blogId);

    void deleteComment(Long commentId);

    Comment findById(Long commentId);
}
