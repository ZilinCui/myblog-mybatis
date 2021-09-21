package top.cuizilin.blog.dao;

import org.springframework.stereotype.Repository;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.vo.BlogAppreciations;
import top.cuizilin.blog.vo.BlogView;

import java.util.List;

@Repository
public interface BlogMapper {
    List<Blog> getUserBlogs(Integer id);

    List<Blog> getBlogList();

    int addBlog(Blog blog);

    Blog getBlogById(Integer id);

    int updateBlog(Blog blog);

    int deleteBlog(Integer id);

    int setRecommended(Integer id, int flag);

    //根据title和typeId查询blog
    List<Blog> getBlogListByIdAndTitle(Integer typeId, String title);

    //后台根据用户信息动态查询博客
    List<Blog> getBlogListByIdAndTitleAndUser(Integer typeId, String title, Integer userId);


    //观看数加1
    int addView(BlogView blogView);

    //点赞数加1
    int addAppreciation(BlogAppreciations blogAppreciations);

    //根据typeId获取对应的Blog
    List<Blog> getBlogListByTypeId(Integer id);

    //根据title模糊查询
    List<Blog> getBlogListByTitle(String title);

    //根据日期将blog排序
    List<Blog> getBlogListOrderByTime();
}
