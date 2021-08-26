package top.cuizilin.blog.dao;


import org.springframework.stereotype.Repository;
import top.cuizilin.blog.pojo.Comment;

import java.util.List;

@Repository
public interface CommentMapper {

    int addComment(Comment comment);

    List<Comment> getCommentListByBlogId(Integer blogId);

    //根据parentId查询其所有子评论
    List<Comment> getReplyComment(Integer id);

    //根据parentId查询其parent的nickname
    String getParentNickname(Integer parentId);
}
