package com.leekejin.blog.controller.admin;


import com.leekejin.blog.service.UserService;
import com.leekejin.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;



@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String loginPage() {
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes attributes) {

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.code(password));
        try {
            subject.login(token);
            return "admin/index";
        } catch (UnknownAccountException e) {
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/admin";
        } catch (IncorrectCredentialsException e) {
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "admin/login";
    }

}
