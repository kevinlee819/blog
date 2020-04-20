package com.leekejin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Type(String name) {
        this.name = name;
    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}