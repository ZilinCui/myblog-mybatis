package top.cuizilin.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.pojo.Information;
import top.cuizilin.blog.pojo.Type;
import top.cuizilin.blog.pojo.User;
import top.cuizilin.blog.service.BlogService;
import top.cuizilin.blog.service.InformationService;
import top.cuizilin.blog.service.TypeService;
import top.cuizilin.blog.vo.BlogAppreciations;
import top.cuizilin.blog.vo.BlogView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private InformationService informationService;

    @GetMapping
    public String index(@RequestParam(required = false, defaultValue = "1", value = "pageNo") Integer pageNo, Model model){
        PageHelper.startPage(pageNo, 3);
        model.addAttribute("pageInfo", new PageInfo<Blog>(blogService.getBlogList()));
        return "index";
    }

    @GetMapping("/blog")
    public String blog(Integer id, Model model){
        Blog blog = blogService.getBlogById(id, false);
        /*调整图片大小*/
        blog.setPhoto(blog.getPhoto().replace("800/515", "1200/600"));
        BlogView blogView = new BlogView(id, blog.getViews() + 1);
        /*观看数加一*/
        blogService.addView(blogView);
        blog.setViews(blogView.getViews());
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/like")
    @ResponseBody
    public String like(Integer id, HttpServletResponse response, Model model) throws IOException {
        Blog blog = blogService.getBlogById(id, false);
        BlogAppreciations blogAppreciations = new BlogAppreciations(blog.getId(), blog.getAppreciations() + 1, "");
        blogService.addAppreciations(blogAppreciations);
        blogAppreciations.setMessage("感谢您的支持");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(blogAppreciations);
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }


    //搜索博客
    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "1", value = "pageNo") Integer pageNo, String title, Model model){
        PageHelper.startPage(pageNo, 3);
        model.addAttribute("pageInfo", new PageInfo<Blog>(blogService.getBlogListByTitle(title)));
        return "search";
    }


    //底部模板
    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model){
        List<Blog> blogList = blogService.getBlogListOrderByTime();
        if(blogList.size() >= 3)
            model.addAttribute("blogs", blogList.subList(0, 3));
        else
            model.addAttribute("blogs", blogList);
        return "_fragments :: newBlogList";
    }
    //关于本站的信息
    @GetMapping("/footer/informationList")
    public String getInformation(Model model){
        Information information = informationService.getInfo();

        Integer allViews = information.getAllViews() + 1;
        informationService.addViews(allViews);
        information.setAllViews(allViews);

        List<Information> informationList = new ArrayList<>();
        informationList.add(information);
        model.addAttribute("informationList", informationList);

        return "_fragments :: informationList";
    }

}
