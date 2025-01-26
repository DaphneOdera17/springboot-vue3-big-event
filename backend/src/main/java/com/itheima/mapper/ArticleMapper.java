package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Birdy
 * @date 2025/1/25 15:27
 * @description ArticleMapper
 */
@Mapper
public interface ArticleMapper {

    /**
     * 添加文章
     * @param article
     */
    @Insert("insert into article(title, content, cover_img, category_id, create_user, create_time, update_time) " +
            "values(#{title}, #{content}, #{coverImg}, #{categoryId}, #{createUser}, now(), now()) ")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @Select("select * from article where id=#{id}")
    Article detail(Integer id);

    /**
     * 修改文章
     * @param article
     */
    @Update("update article set " +
            "title=#{title}, content=#{content}, " +
            "cover_img=#{coverImg}, state=#{state}, " +
            "category_id=#{categoryId}, update_time=now()" +
            " where id=#{id}")
    void update(Article article);

    /**
     * 删除文章
     * @param id
     */
    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
