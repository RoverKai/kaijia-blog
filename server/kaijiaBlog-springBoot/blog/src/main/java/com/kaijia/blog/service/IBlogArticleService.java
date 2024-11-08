package com.kaijia.blog.service;

import java.util.List;

import com.kaijia.blog.controller.vo.VArticle;
import com.kaijia.blog.domain.BlogArticle;

/**
 * 文章管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-21
 */
public interface IBlogArticleService 
{
    /**
     * 查询文章管理
     * 
     * @param id 文章管理主键
     * @return 文章管理
     */
    public BlogArticle selectBlogArticleById(Long id, Long userId);
    public BlogArticle updateViewCount(BlogArticle blogArticle);
    public boolean hasUserLikedArticle(Long articleId, Long userId);

    /**
     * 查询文章管理列表
     * 
     * @param blogArticle 文章管理
     * @return 文章管理集合
     */
    public List<BlogArticle> selectBlogArticleList(BlogArticle blogArticle);
    public List<VArticle> list4User(VArticle vArticle);
    /**
     * 新增文章管理
     * 
     * @param blogArticle 文章管理
     * @return 结果
     */
    public int insertBlogArticle(BlogArticle blogArticle);

    /**
     * 修改文章管理
     * 
     * @param blogArticle 文章管理
     * @return 结果
     */
    public int updateBlogArticle(BlogArticle blogArticle);

    /**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的文章管理主键集合
     * @return 结果
     */
    public int deleteBlogArticleByIds(Long[] ids);

    /**
     * 删除文章管理信息
     * 
     * @param id 文章管理主键
     * @return 结果
     */
    public int deleteBlogArticleById(Long id);
}