package com.kaijia.blog.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaijia.blog.mapper.BlogArticleLikeMapper;
import com.kaijia.blog.domain.BlogArticleLike;
import com.kaijia.blog.service.IBlogArticleLikeService;

/**
 * 点赞管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-21
 */
@Service
public class BlogArticleLikeServiceImpl implements IBlogArticleLikeService {
    @Autowired
    private BlogArticleLikeMapper blogArticleLikeMapper;

    /**
     * 查询点赞管理
     *
     * @param articleId 点赞管理主键
     * @return 点赞管理
     */
    @Override
    public List<BlogArticleLike> selectBlogArticleLikeByArticleId(Long articleId) {
        return blogArticleLikeMapper.selectBlogArticleLikeByArticleId(articleId);
    }

    /**
     * 查询点赞管理列表
     *
     * @param blogArticleLike 点赞管理
     * @return 点赞管理
     */
    @Override
    public List<BlogArticleLike> selectBlogArticleLikeList(BlogArticleLike blogArticleLike) {
        return blogArticleLikeMapper.selectBlogArticleLikeList(blogArticleLike);
    }

    /**
     * 新增点赞管理
     *
     * @param blogArticleLike 点赞管理
     * @return 结果
     */
    @Override
    public int insertBlogArticleLike(BlogArticleLike blogArticleLike) {
        blogArticleLike.setCreateTime(DateUtils.getNowDate());
        return blogArticleLikeMapper.insertBlogArticleLike(blogArticleLike);
    }

    /**
     * 修改点赞管理
     *
     * @param blogArticleLike 点赞管理
     * @return 结果
     */
    @Override
    public int updateBlogArticleLike(BlogArticleLike blogArticleLike) {
        return blogArticleLikeMapper.updateBlogArticleLike(blogArticleLike);
    }

    /**
     * 批量删除点赞管理
     *
     * @param articleIds 需要删除的点赞管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleLikeByArticleIds(Long[] articleIds) {
        return blogArticleLikeMapper.deleteBlogArticleLikeByArticleIds(articleIds);
    }

    /**
     * 删除点赞管理信息
     *
     * @param articleId 点赞管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleLikeByArticleId(Long articleId) {
        return blogArticleLikeMapper.deleteBlogArticleLikeByArticleId(articleId);
    }

    /**
     * 删除目标赞
     * @param targetLike 目标赞: Map: (String, Long)
     * @return
     */
    @Override
    public int deleteBlogArticleLikeByArticleIdAndUserId(Map<String, Long> targetLike) {
        return blogArticleLikeMapper.deleteBlogArticleLikeByArticleIdAndUserId(targetLike);
    }
}