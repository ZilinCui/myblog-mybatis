package top.cuizilin.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.cuizilin.blog.pojo.User;
import top.cuizilin.blog.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexLoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String goLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, RedirectAttributes attributes, HttpSession session){
        User user = userService.checkUser(username, password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user", user);
            return "redirect:/";
        }
        attributes.addFlashAttribute("message", "用户名或密码错误");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/registered")
    public String goRegistered(){
        return "registered";
    }

    @PostMapping("/registered")
    public String registered(User user, RedirectAttributes attributes){
        User checkUser = userService.searchUserByUsername(user.getUsername());
        if(checkUser != null){
            attributes.addFlashAttribute("message", "用户名已经存在");
            return "redirect:/registered";
        }
        userService.addNewUser(user);
        return "login";
    }
}
