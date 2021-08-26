package top.cuizilin.blog.dao;

import org.springframework.stereotype.Repository;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.pojo.Type;

import java.util.List;

@Repository
public interface TypeMapper {

    //查询所有标签
    List<Type> getTypeList();


    //添加标签
    int addType(Type type);


    //根据id找到对应标签
    Type getTypeById(Integer id);


    //修改type
    int updateType(Type type);


    //根据id删除对应type
    int deleteTypeById(Integer id);


    //根据type的id获取其对应的blog
    List<Blog> getBlogType(Integer id);
}
