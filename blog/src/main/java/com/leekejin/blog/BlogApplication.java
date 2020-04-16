package com.leekejin.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
@ComponentScan(basePackages={"com.leekejin.blog.controller"})
@SpringBootApplication()
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}