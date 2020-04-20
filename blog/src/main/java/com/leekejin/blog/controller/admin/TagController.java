package com.leekejin.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leekejin.blog.pojo.Tag;
import com.leekejin.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model) {
        PageHelper.startPage(pagenum, 10);
        List<Tag> allTags = tagService.getAllTags();
        //得到分页结果对象
        PageInfo<Tag> pageInfo = new PageInfo<>(allTags);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    //新增分类
    @PostMapping("/tags/add")
    public String toAddTag(HttpServletRequest request, RedirectAttributes attributes){
        String tagName = request.getParameter("name");
        Tag tag = tagService.getTagByName(tagName);
        if (tag != null){
            attributes.addFlashAttribute("msgF","标签已重复！");
            return "redirect:/admin/tags";
        } else {
            attributes.addFlashAttribute("msgS","添加成功");
            tagService.saveTag(new Tag(tagName));
            return "redirect:/admin/tags";
        }

    }


    //删除分类
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msgS", "删除成功");
        return "redirect:/admin/tags";
    }

    //编辑分类
    @PostMapping("/tags/edit")
    public String editTag(HttpServletRequest request, RedirectAttributes attributes){  //修改
        String tagName = request.getParameter("name");
        Long tagId = Long.valueOf(request.getParameter("id"));
        Tag tag = tagService.getTagByName(tagName);
        if (tag != null){
            attributes.addFlashAttribute("msgF","当前标签名已存在");
            return "redirect:/admin/tags";
        } else {
            attributes.addFlashAttribute("msgS","修改成功");
            tagService.updateTag(new Tag(tagId, tagName));
            return "redirect:/admin/tags";
        }
    }
}
