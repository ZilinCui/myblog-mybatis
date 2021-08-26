package top.cuizilin.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.cuizilin.blog.dao.TypeMapper;
import top.cuizilin.blog.pojo.Type;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeMapper typeMapper;

    public List<Type> getTypeList(){
        return typeMapper.getTypeList();
    }

    public int addType(Type type){
        return typeMapper.addType(type);
    }

    //根据id找到分类
    public Type getTypeById(Integer id){
        return typeMapper.getTypeById(id);
    }


    //修改分类名称
    public int updateType(Type type){
        return typeMapper.updateType(type);
    }

    //根据id删除对应type
    public int deleteTypeById(Integer id){
        return typeMapper.deleteTypeById(id);
    }

    //获取所有的type以及它对应的blog
    public List<Type> getTypeListAndItsBlog(){
        List<Type> typeList = typeMapper.getTypeList();
        for(Type type : typeList){
            type.setBlogList(typeMapper.getBlogType(type.getId()));
        }
        return typeList;
    }
}
