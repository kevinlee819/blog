package com.leekejin.blog.controller;


import com.github.pagehelper.PageHelper;
import com.leekejin.blog.pojo.Type;
import com.leekejin.blog.service.BlogService;
import com.leekejin.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id,
                        @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model) {
        PageHelper.startPage(pagenum, 15);
        List<Type> types = typeService.getBlogType();
        return "types";
    }
}
