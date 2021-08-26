package top.cuizilin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    //主键
    private Integer id;

    //外键
    private Integer userId;
    private Integer typeId;

    //内容
    private String title;
    private String content;
    private String photo;
    private String description;
    private Integer views;
    private Integer appreciations;
    private Boolean isPublished;
    private Boolean isRecommended;

    private String updateTime;

    private Type type;

    private User user;

}
