package com.kaijia.blog.mapper;

import java.util.List;

import com.kaijia.blog.controller.vo.VArticle;
import com.kaijia.blog.controller.vo.VComment;
import com.kaijia.blog.domain.BlogComment;

/**
 * 评论管理Mapper接口
 * 
 * @author kaijia
 * @date 2024-10-22
 */
public interface BlogCommentMapper 
{
    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    public BlogComment selectBlogCommentById(Long id);

    /**
     * 查询评论管理列表
     * 
     * @param blogComment 评论管理
     * @return 评论管理集合
     */
    public List<BlogComment> selectBlogCommentList(BlogComment blogComment);
    public List<VComment> selectComment4Article(VComment vComment);

    public List<VComment> selectComment4Article(VArticle vArticle);
    /**
     * 新增评论管理
     * 
     * @param blogComment 评论管理
     * @return 结果
     */
    public int insertBlogComment(BlogComment blogComment);

    /**
     * 修改评论管理
     * 
     * @param blogComment 评论管理
     * @return 结果
     */
    public int updateBlogComment(BlogComment blogComment);

    /**
     * 删除评论管理
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteBlogCommentById(Long id);

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogCommentByIds(Long[] ids);
}
