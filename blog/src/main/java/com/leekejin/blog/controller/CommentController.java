package com.leekejin.blog.controller;

import com.leekejin.blog.pojo.Comment;
import com.leekejin.blog.pojo.User;
import com.leekejin.blog.service.BlogService;
import com.leekejin.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        //model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")   //提交留言
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlogId();
//        comment.setBlog(blogService.getDetailedBlog(blogId));  //绑定博客与评论
//        comment.setBlogId(blogId);
        User user = (User) session.getAttribute("user");
        if (user != null){   //用户为管理员
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setType(1);
        }else {
            comment.setAvatar(avatar);
            comment.setType(2);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

    @GetMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable Long id){
        Comment comment = commentService.findById(id);
        Long blogId = comment.getBlogId();
        commentService.deleteComment(id);
        return "redirect:/blog/" + blogId;
    }
}
