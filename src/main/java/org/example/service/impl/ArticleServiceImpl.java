package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.example.mapper.ArticleMapper;
import org.example.pojo.Article;
import org.example.pojo.PageBean;
import org.example.service.ArticleService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //1.创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //3.调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list2(userId,categoryId,state);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Article article) {

        // 设置更新时间
        article.setUpdateTime(LocalDateTime.now());

        // 调用mapper执行更改操作
        articleMapper.update(article);
        // 还可以根据需要添加其他相关的操作
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
        // 还可以根据需要添加其他相关的操作
    }
}
