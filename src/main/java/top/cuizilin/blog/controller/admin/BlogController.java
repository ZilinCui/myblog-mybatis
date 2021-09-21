package top.cuizilin.blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.pojo.Type;
import top.cuizilin.blog.pojo.User;
import top.cuizilin.blog.service.BlogService;
import top.cuizilin.blog.service.TypeService;
import top.cuizilin.blog.utils.PhotoUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false, defaultValue = "1", value = "pageNo") Integer pageNo,
                        Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer id = null;
        if(user != null){ id = user.getId(); }
        PageHelper.startPage(pageNo, 5);
        model.addAttribute("pageInfo", new PageInfo<Blog>(blogService.getUserBlogs(id)));
        model.addAttribute("typeList", typeService.getTypeList());
        model.addAttribute("flag", "show");
        return "admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        List<Type> typeList = typeService.getTypeList();
        model.addAttribute("typeList", typeList);
        model.addAttribute("blog", new Blog());
        return "admin/blog-input";
    }

    @PostMapping("/blogs/input")
    public String inputBlog(Blog blog, HttpSession session){
        Integer id = blog.getId();
        //如果没有对应的blog,则新增
        if(id == null){
            //拿到userId,去service层添加其他字段
            blog.setPhoto(PhotoUtils.createNewPhoto());
            User user = (User)session.getAttribute("user");
            blog.setUserId(user.getId());
            blogService.addBlog(blog);
            return "redirect:/admin/blogs";
        }
        //如果有对应的blog，则修改
        blogService.updateBlog(blog);
        return "redirect:/admin/blogs";

    }

    @GetMapping("/blogs/update")
    public String update(Integer id, Model model){
        Blog blog = blogService.getBlogById(id, true);
        blog.setType(typeService.getTypeById(blog.getTypeId()));
        List<Type> typeList = typeService.getTypeList();
        model.addAttribute("typeList", typeList);
        model.addAttribute("blog", blog);
            return "admin/blog-input";
    }

    //根据id删除博客
    @GetMapping("/blogs/delete")
    public String delete(Integer id, Model model){
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/recommend")
    public String recommend(Integer id){
        blogService.setRecommended(id);
        return "redirect:/admin/blogs";
    }

    //根据标题和分类搜索博客
    @PostMapping("/blogs/search")
    public String search(@RequestParam(required = false, defaultValue = "1", value = "pageNo") Integer pageNo,
                         Integer typeId, String title, Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer id = null;
        if(user != null) id = user.getId();
        PageHelper.startPage(pageNo, 8);
        model.addAttribute("pageInfo", new PageInfo<Blog>(blogService.getBlogListByTypeIdAndTitleAndUser(typeId, title, id)));
        model.addAttribute("typeList", typeService.getTypeList());
        model.addAttribute("flag", null);
        return "admin/blogs :: blogList";
    }
}
