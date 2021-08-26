package top.cuizilin.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.cuizilin.blog.dao.BlogMapper;
import top.cuizilin.blog.dao.TypeMapper;
import top.cuizilin.blog.dao.UserMapper;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.utils.DateUtils;
import top.cuizilin.blog.utils.MarkDownUtils;
import top.cuizilin.blog.vo.BlogAppreciations;
import top.cuizilin.blog.vo.BlogView;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Blog> getBlogList(){
        List<Blog> blogList = blogMapper.getBlogList();
        for(Blog blog : blogList){
            blog.setType(typeMapper.getTypeById(blog.getTypeId()));
            blog.setUser(userMapper.getUserById(blog.getUserId()));
        }
        return blogList;
    }

    public int addBlog(Blog blog){
        blog.setUpdateTime(DateUtils.formatDate(new Date()));
        blog.setViews(0);
        blog.setAppreciations(0);
        blog.setIsRecommended(false);
        return blogMapper.addBlog(blog);
    }

    //判断是前端还是后台获取blog 后台为true
    public Blog getBlogById(Integer id, boolean flag){
        Blog blog = blogMapper.getBlogById(id);
        blog.setType(typeMapper.getTypeById(blog.getTypeId()));
        blog.setUser(userMapper.getUserById(blog.getUserId()));
        if(!flag){
            blog.setContent(MarkDownUtils.extVideo(MarkDownUtils.markdownToHtmlExtensions(blog.getContent())));
        }
        return blog;
    }




    public int updateBlog(Blog blog){
        blog.setUpdateTime(DateUtils.formatDate(new Date()));
        return blogMapper.updateBlog(blog);
    }

    public int deleteBlog(Integer id){
        return blogMapper.deleteBlog(id);
    }

    //设置推荐为true
    public int setRecommended(Integer id){
        return blogMapper.setRecommended(id, blogMapper.getBlogById(id).getIsRecommended() ? 0 : 1);
    }

    //动态查询博客
    public List<Blog> getBlogListByTypeIdAndTitle(Integer typeId, String title){
        //设置blog的Type
        List<Blog> blogList = blogMapper.getBlogListByIdAndTitle(typeId, title);
        for(Blog blog : blogList){
            blog.setType(typeMapper.getTypeById(blog.getTypeId()));
        }
        return blogList;
    }

    public int addView(BlogView blogView){
        return blogMapper.addView(blogView);
    }

    public int addAppreciations(BlogAppreciations blogAppreciations){ return blogMapper.addAppreciation(blogAppreciations); }

    public List<Blog> getBlogListByTypeId(Integer id){
       List<Blog> blogList = blogMapper.getBlogListByTypeId(id);
        for(Blog blog : blogList){
            blog.setType(typeMapper.getTypeById(blog.getTypeId()));
            blog.setUser(userMapper.getUserById(blog.getUserId()));
        }
        return blogList;
    }

    //根据标题进行模糊查询
    public List<Blog> getBlogListByTitle(String title){
        List<Blog> blogList = blogMapper.getBlogListByTitle(title);
        for(Blog blog : blogList){
            blog.setType(typeMapper.getTypeById(blog.getTypeId()));
            blog.setUser(userMapper.getUserById(blog.getUserId()));
        }
        return blogList;
    }
}
