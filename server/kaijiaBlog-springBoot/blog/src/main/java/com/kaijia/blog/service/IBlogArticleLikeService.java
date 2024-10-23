package com.kaijia.blog.service;

import java.util.List;
import com.kaijia.blog.domain.BlogArticleLike;

/**
 * 点赞管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-21
 */
public interface IBlogArticleLikeService 
{
    /**
     * 查询点赞管理
     * 
     * @param articleId 点赞管理主键
     * @return 点赞管理
     */
    public BlogArticleLike selectBlogArticleLikeByArticleId(Long articleId);

    /**
     * 查询点赞管理列表
     * 
     * @param blogArticleLike 点赞管理
     * @return 点赞管理集合
     */
    public List<BlogArticleLike> selectBlogArticleLikeList(BlogArticleLike blogArticleLike);

    /**
     * 新增点赞管理
     * 
     * @param blogArticleLike 点赞管理
     * @return 结果
     */
    public int insertBlogArticleLike(BlogArticleLike blogArticleLike);

    /**
     * 修改点赞管理
     * 
     * @param blogArticleLike 点赞管理
     * @return 结果
     */
    public int updateBlogArticleLike(BlogArticleLike blogArticleLike);

    /**
     * 批量删除点赞管理
     * 
     * @param articleIds 需要删除的点赞管理主键集合
     * @return 结果
     */
    public int deleteBlogArticleLikeByArticleIds(Long[] articleIds);

    /**
     * 删除点赞管理信息
     * 
     * @param articleId 点赞管理主键
     * @return 结果
     */
    public int deleteBlogArticleLikeByArticleId(Long articleId);
}