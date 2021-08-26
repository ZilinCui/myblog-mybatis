package top.cuizilin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Integer id;
    private String name;

    public Type(Integer id, String name){ this.id = id; this.name = name; }

    private List<Blog> blogList;
}
