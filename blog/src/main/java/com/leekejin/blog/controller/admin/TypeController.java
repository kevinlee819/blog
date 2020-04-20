package com.leekejin.blog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leekejin.blog.pojo.Type;
import com.leekejin.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model) {
        PageHelper.startPage(pagenum, 10);
        List<Type> allType = typeService.getAllType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    //新增分类
    @PostMapping("/types/add")
    public String toAddType(HttpServletRequest request, RedirectAttributes attributes){
        String typeName = request.getParameter("name");
        Type type = typeService.getTypeByName(typeName);
        if (type != null){
            attributes.addFlashAttribute("msgF","分类已重复！");
            return "redirect:/admin/types";
        } else {
            attributes.addFlashAttribute("msgS","添加成功");
            typeService.saveType(new Type(typeName));
            return "redirect:/admin/types";
        }

    }


    //删除分类
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("msgS", "删除成功");
        return "redirect:/admin/types";
    }

    //编辑分类
    @PostMapping("/types/edit")
    public String editType(HttpServletRequest request, RedirectAttributes attributes){  //修改
        String typeName = request.getParameter("name");
        Long typeId = Long.valueOf(request.getParameter("id"));
        Type type = typeService.getTypeByName(typeName);
        if (type != null){
            attributes.addFlashAttribute("msgF","当前分类名已存在");
            return "redirect:/admin/types";
        } else {
            attributes.addFlashAttribute("msgS","修改成功");
            typeService.updateType(new Type(typeId, typeName));
            return "redirect:/admin/types";
        }
    }
}
