package com.leekejin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;//地址相等
        }

        if (obj == null){
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if (obj instanceof Tag) {
            Tag other = (Tag) obj;
            if (((Tag) obj).getId().toString().equals(this.getId().toString()) &&
                    ((Tag) obj).getName().equals(this.getName())) {
                return true;
            }
        }
        return false;
    }
}