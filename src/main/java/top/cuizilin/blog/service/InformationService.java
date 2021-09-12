package top.cuizilin.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.cuizilin.blog.dao.InformationMapper;
import top.cuizilin.blog.pojo.Information;

@Service
public class InformationService {
    @Autowired
    private InformationMapper informationMapper;

    public Information getInfo(){
        Integer blogCount = informationMapper.getBlogCount();
        Integer blogViews = informationMapper.getAllViews();
        Integer blogLoves = informationMapper.getAllLoves();
        Information information = new Information();
        information.setAllBlogs(blogCount);
        information.setAllViews(blogViews);
        information.setAllLoves(blogLoves);
        return information;
    }

    public int addViews(Integer currentView){
        return informationMapper.addViews(currentView);
    }
}
