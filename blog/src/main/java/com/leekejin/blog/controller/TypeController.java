package com.leekejin.blog.controller;

import com.leekejin.blog.dao.TypeDao;
import com.leekejin.blog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private TypeDao typeDao;
    @RequestMapping("/getTypeName")
    public List<Type> gettype(){
        return typeDao.getAllType();
    }
}
