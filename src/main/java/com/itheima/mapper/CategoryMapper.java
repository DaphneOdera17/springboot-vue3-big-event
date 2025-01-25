package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Birdy
 * @date 2025/1/25 13:11
 * @description CategoryMapper
 */
@Mapper
public interface CategoryMapper {

    /**
     * 添加分类
     * @param category
     */
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) values" +
            "(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    /**
     * 删除分类
     * @param id
     */
    @Delete("delete from category where id=#{id}")
    void delete(Integer id);

    /**
     * 查询分类
     * @param userId
     * @return
     */
    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    /**
     * 根据 id 查询分类信息
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category findById(Integer id);

    /**
     * 更新分类信息
     * @param category
     */
    @Update("update category set category_name=#{categoryName}," +
            "category_alias=#{categoryAlias}," +
            "update_time=now() where id=#{id}")
    void update(Category category);
}
