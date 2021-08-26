package top.cuizilin.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.cuizilin.blog.dao.CommentMapper;
import top.cuizilin.blog.pojo.Comment;
import top.cuizilin.blog.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public int addComment(Comment comment){
        comment.setUpdateTime(DateUtils.formatDate(new Date()));
        return commentMapper.addComment(comment);
    }

    public List<Comment> getCommentListByBlogId(Integer id){
        List<Comment> commentList = commentMapper.getCommentListByBlogId(id);
        //广度优先算法
        for(Comment comment : commentList){

            //将这个comment的子comment全部加载到resultComment（临时区域）中
            getAllReplyComments(comment);

            comment.getReplyList().addAll(resultComment);

            //清空临时区域
            resultComment.clear();
        }
        return commentList;
    }

    //临时区域
    private List<Comment> resultComment = new ArrayList<>();



    public void getAllReplyComments(Comment comment){
        List<Comment> replies = commentMapper.getReplyComment(comment.getId());
        if(replies.size() > 0){
            addParentNickName(replies);
            resultComment.addAll(replies);
            for(Comment comment1 : replies){
                //递归调用
                comment1.setParentNickname(commentMapper.getParentNickname(comment1.getParentId()));
                getAllReplyComments(comment1);
            }
        }
    }

    public void addParentNickName(List<Comment> commentList){
        for(Comment comment : commentList){
            comment.setParentNickname(commentMapper.getParentNickname(comment.getParentId()));
        }
    }
}
