package com.itheima.service;

import com.itheima.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Birdy
 * @date 2025/1/25 13:11
 * @description CategoryService
 */
public interface CategoryService {
    /**
     * 新增分类
     * @param category
     */
    void add(Category category);

    /**
     * 删除分类
     * @param id
     */
    void delete(Integer id);

    /**
     * 列表查询
     * @return
     */
    List<Category> list();

    /**
     * 根据 id 查询分类信息
     * @param id
     * @return
     */
    Category findById(Integer id);

    /**
     * 更新分类信息
     * @param category
     */
    void update(Category category);
}
