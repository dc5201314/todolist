package org.example.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);


    List<Article>list2(Integer userId, Integer categoryId, String state);

    // 更新文章
    @Update("UPDATE article SET title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = #{updateTime} WHERE id = #{id}")
    void update(Article article);


    // 删除文章
    @Delete("DELETE FROM article WHERE id = #{id}")
    void delete(Integer id);
}
