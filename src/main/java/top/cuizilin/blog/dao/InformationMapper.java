package top.cuizilin.blog.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface InformationMapper {
    Integer getBlogCount();

    Integer getAllViews();

    Integer getAllLoves();

    int addViews(Integer currentView);

}
