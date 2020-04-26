package com.leekejin.blog.service.impl;

import com.leekejin.blog.dao.CommentDao;
import com.leekejin.blog.pojo.Comment;
import com.leekejin.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    public CommentDao commentDao;

    @Transactional
    @Override
    public List<Comment> getCommentByBlogId(Long blogId) { // 这里获得的是父ID == -1的评论
        List<Comment> comments = commentDao.findByBlogIdAndParentCommentNull(blogId, Long.parseLong("-1"));
        return comments;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
//        //获得父id
//        Long parentCommentId = comment.getParentCommentId();
//        //没有父级评论默认是-1
//        if (parentCommentId != -1) {
//            //有父级评论
//            comment.setParentComment(commentDao.findByParentCommentId(parentCommentId));
//        } else {
//            //没有父级评论
//            comment.setParentCommentId((long) -1);
//            comment.setParentComment(null);
//        }
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }

    @Transactional
    @Override
    public List<Comment> listCommentByBlogId(Long blogId){
        List<Comment> ancesterComments = getCommentByBlogId(blogId);
        for (Comment ancester : ancesterComments) {
            List<Comment> sonComments = commentDao.findByAncesterCommentId(ancester.getId());
            if (!sonComments.isEmpty()){
                for (Comment sonComment : sonComments) {
                    sonComment.setParentComment(commentDao.findById(sonComment.getParentCommentId()));
                }
                ancester.setReplyComments(sonComments);
            }
        }
        return ancesterComments;
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        commentDao.deleteOneComment(commentId);
        if (commentDao.findByParentCommentId(commentId) != null) {
            commentDao.deleteOneComment(commentId);
        }
    }

    @Transactional
    @Override
    public Comment findById(Long commentId) {
        return commentDao.findById(commentId);
    }
}
