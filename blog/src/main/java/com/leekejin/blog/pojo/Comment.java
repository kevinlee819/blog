package com.leekejin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;

    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}