package com.kaijia.blog.service.impl;

import java.util.List;

import com.kaijia.blog.controller.vo.VArticle;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaijia.blog.mapper.BlogArticleMapper;
import com.kaijia.blog.domain.BlogArticle;
import com.kaijia.blog.service.IBlogArticleService;

/**
 * 文章管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-21
 */
@Service
public class BlogArticleServiceImpl implements IBlogArticleService 
{
    @Autowired
    private BlogArticleMapper blogArticleMapper;

    /**
     * 查询文章管理
     * 
     * @param id 文章管理主键
     * @return 文章管理
     */
    @Override
    public BlogArticle selectBlogArticleById(Long id)
    {
        BlogArticle blogArticle = blogArticleMapper.selectBlogArticleById(id);
        Long viewCount = blogArticle.getViewCount();
        blogArticle.setViewCount(viewCount+1);
        blogArticleMapper.updateBlogArticle(blogArticle);
        return blogArticle;
    }

    @Override
    public List<VArticle> list4User(VArticle vArticle) {
        return blogArticleMapper.list4User(vArticle);
    }
    /**
     * 查询文章管理列表
     * 
     * @param blogArticle 文章管理
     * @return 文章管理
     */
    @Override
    public List<BlogArticle> selectBlogArticleList(BlogArticle blogArticle)
    {
        List<BlogArticle> blogArticles = blogArticleMapper.selectBlogArticleList(blogArticle);
        blogArticles.stream().forEach(article -> {
            article.setContent(null);
        });
        return blogArticles;
    }

    /**
     * 新增文章管理
     * 
     * @param blogArticle 文章管理
     * @return 结果
     */
    @Override
    public int insertBlogArticle(BlogArticle blogArticle)
    {
        blogArticle.setCreateTime(DateUtils.getNowDate());
        return blogArticleMapper.insertBlogArticle(blogArticle);
    }

    /**
     * 修改文章管理
     * 
     * @param blogArticle 文章管理
     * @return 结果
     */
    @Override
    public int updateBlogArticle(BlogArticle blogArticle)
    {
        blogArticle.setUpdateTime(DateUtils.getNowDate());
        return blogArticleMapper.updateBlogArticle(blogArticle);
    }

    /**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的文章管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleByIds(Long[] ids)
    {
        return blogArticleMapper.deleteBlogArticleByIds(ids);
    }

    /**
     * 删除文章管理信息
     * 
     * @param id 文章管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleById(Long id)
    {
        return blogArticleMapper.deleteBlogArticleById(id);
    }
}