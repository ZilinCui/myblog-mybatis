package top.cuizilin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private String nickname;
    private String content;
    private String updateTime;
    private Integer blogId;
    private Integer parentId;

    private String parentNickname;

    //广度优先搜索
    private List<Comment> replyList = new ArrayList<>();
}
