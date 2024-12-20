package com.kaijia.blog.service.impl;

import java.util.List;

import com.kaijia.blog.controller.vo.VComment;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaijia.blog.mapper.BlogCommentMapper;
import com.kaijia.blog.domain.BlogComment;
import com.kaijia.blog.service.IBlogCommentService;

/**
 * 评论管理Service业务层处理
 * 
 * @author kaijia
 * @date 2024-10-22
 */
@Service
public class BlogCommentServiceImpl implements IBlogCommentService 
{
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    @Override
    public BlogComment selectBlogCommentById(Long id)
    {
        return blogCommentMapper.selectBlogCommentById(id);
    }

    /**
     * 查询评论管理列表
     * 
     * @param blogComment 评论管理
     * @return 评论管理
     */
    @Override
    public List<VComment> selectBlogCommentList(VComment blogComment)
    {
        return blogCommentMapper.selectBlogCommentList(blogComment);
    }

    /**
     * 用户查询评论
     */
    public List<VComment> selectComment4Article(VComment vComment)
    {
        return blogCommentMapper.selectComment4Article(vComment);
    }

    /**
     * 新增评论管理
     * 
     * @param blogComment 评论管理
     * @return 结果
     */
    @Override
    public int insertBlogComment(BlogComment blogComment)
    {
        blogComment.setCreateTime(DateUtils.getNowDate());
        return blogCommentMapper.insertBlogComment(blogComment);
    }

    /**
     * 修改评论管理
     * 
     * @param blogComment 评论管理
     * @return 结果
     */
    @Override
    public int updateBlogComment(BlogComment blogComment)
    {
        return blogCommentMapper.updateBlogComment(blogComment);
    }

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的评论管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogCommentByIds(Long[] ids)
    {
        return blogCommentMapper.deleteBlogCommentByIds(ids);
    }

    /**
     * 删除评论管理信息
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogCommentById(Long id)
    {
        return blogCommentMapper.deleteBlogCommentById(id);
    }
}
