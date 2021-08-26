package top.cuizilin.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.cuizilin.blog.dao.UserMapper;
import top.cuizilin.blog.pojo.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User checkUser(String username, String password){
        User user = userMapper.checkUser(username, password);
        return user;
    }

    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }


    //查询用户是不是管理员
    public User checkAdmin(String username, String password){
        return userMapper.checkAdmin(username, password);
    }

    //根据用户名查询用户
    public User searchUserByUsername(String username){
        return userMapper.searchUserByUsername(username);
    }

    //创建新用户
    public int addNewUser(User user){
        user.setType(0);
        user.setAvatar("https://unsplash.it/100/100?image=1005");
        return userMapper.addNewUser(user);
    }
}
