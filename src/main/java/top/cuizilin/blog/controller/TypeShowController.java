package top.cuizilin.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.pojo.Type;
import top.cuizilin.blog.service.BlogService;
import top.cuizilin.blog.service.TypeService;

import java.util.List;

@Controller
@RequestMapping("/types")
public class TypeShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String types(@RequestParam(required = false, defaultValue = "1", value = "pageNo") Integer pageNo, Integer id, Model model){
        List<Type> typeList = typeService.getTypeListAndItsBlog();
        model.addAttribute("typeList", typeList);
        if(id == -1){
            id = typeList.get(0).getId();
        }

        PageHelper.startPage(pageNo, 1000);
        List<Blog> blogList = blogService.getBlogListByTypeId(id);
        model.addAttribute("pageInfo", new PageInfo<Blog>(blogList));
        model.addAttribute("activeId", id);
        return "types";
    }
}
