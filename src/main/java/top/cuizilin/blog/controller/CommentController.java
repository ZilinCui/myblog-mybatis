package top.cuizilin.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.pojo.Comment;
import top.cuizilin.blog.service.BlogService;
import top.cuizilin.blog.service.CommentService;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/getComments")
    public String getComments(Model model, Integer blogId){
        model.addAttribute("commentList", commentService.getCommentListByBlogId(blogId));
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String comments(Comment comment, Model model){
        commentService.addComment(comment);
        List<Comment> commentList = commentService.getCommentListByBlogId(comment.getBlogId());
        model.addAttribute("commentList", commentList);
        //根据blogId 查出该blog所有对应的comment
        return "blog :: commentList";
    }
}
