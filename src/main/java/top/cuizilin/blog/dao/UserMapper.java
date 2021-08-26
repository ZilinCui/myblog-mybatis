package top.cuizilin.blog.dao;

import org.springframework.stereotype.Repository;
import top.cuizilin.blog.pojo.User;

import java.util.List;

@Repository
public interface UserMapper {

    /*---------根据用户名和密码查询用户------------*/
    User checkUser(String username, String password);

    //查询User是不是管理员
    User checkAdmin(String username, String password);

    //根据id获取User
    User getUserById(Integer id);

    //根据用户名查询user
    User searchUserByUsername(String username);

    //添加新用户
    int addNewUser(User user);
}
