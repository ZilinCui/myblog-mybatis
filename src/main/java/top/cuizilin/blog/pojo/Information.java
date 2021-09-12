package top.cuizilin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private Integer allBlogs;
    private Integer allViews;
    private Integer allLoves;
}
