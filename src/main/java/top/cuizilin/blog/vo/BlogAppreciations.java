package top.cuizilin.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAppreciations {
    private Integer blogId;
    private Integer appreciations;
    private String message;
}
