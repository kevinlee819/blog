package com.leekejin.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String Index(){
        return "index";
    }

//    @GetMapping("/blog")
//    public String blog(){
//        return "blog";
//    }
//
//    @GetMapping("/types")
//    public String type(){
//        return "types";
//    }
}
