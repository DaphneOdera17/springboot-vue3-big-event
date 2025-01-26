package com.itheima.service;

import com.itheima.controller.PageBean;
import com.itheima.pojo.Article;

/**
 * @author Birdy
 * @date 2025/1/25 15:26
 * @description ArticleService
 */
public interface ArticleService {
    /**
     * 添加文章
     * @param article
     */
    void add(Article article);

    /**
     * 文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    /**
     * 文章详情
     * @param id
     * @return
     */
    Article detail(Integer id);

    /**
     * 修改文章
     * @param article
     */
    void update(Article article);

    /**
     * 删除文章
     * @param id
     */
    void delete(Integer id);
}
