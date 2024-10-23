package com.kaijia.blog.service.impl;

import java.util.List;
import java.util.Optional;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 查询文章管理
     * 
     * @param id 文章管理主键
     * @return 文章管理
     */
    @Override
    public BlogArticle selectBlogArticleById(Long id)
    {
        return blogArticleMapper.selectBlogArticleById(id);
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
        return blogArticleMapper.selectBlogArticleList(blogArticle);
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