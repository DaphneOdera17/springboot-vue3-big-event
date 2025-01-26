package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.controller.PageBean;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Birdy
 * @date 2025/1/25 15:26
 * @description ArticleServiceImpl
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建 PageBean 对象
        PageBean<Article> pb = new PageBean<>();
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        // 调用 mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<Article> as = articleMapper.list(userId, categoryId, state);
        Page<Article> p = (Page<Article>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        return pb;
    }

    @Override
    public Article detail(Integer id) {
        return articleMapper.detail(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
