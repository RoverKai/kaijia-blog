package com.kaijia.blog.mapper;

import java.util.List;
import java.util.Map;

import com.kaijia.blog.controller.vo.VArticleLike;
import com.kaijia.blog.domain.BlogArticleLike;

/**
 * 点赞管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-21
 */
public interface BlogArticleLikeMapper 
{
    /**
     * 查询点赞管理
     * 
     * @param articleId 点赞管理主键
     * @return 点赞管理
     */
    public List<BlogArticleLike> selectBlogArticleLikeByArticleId(Long articleId);

    /**
     * 查询点赞管理列表
     * 
     * @param blogArticleLike 点赞管理
     * @return 点赞管理集合
     */
    public List<VArticleLike> selectBlogArticleLikeList(VArticleLike blogArticleLike);

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
     * 删除点赞管理
     * 
     * @param articleId 点赞管理主键
     * @return 结果
     */
    public int deleteBlogArticleLikeByArticleId(Long articleId);

    /**
     * 批量删除点赞管理
     * 
     * @param articleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogArticleLikeByArticleIds(Long[] articleIds);

    public int deleteBlogArticleLikeByArticleIdAndUserId(Map<String, Long> targetLike);
}