package com.kaijia.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaijia.blog.mapper.BlogCategoryMapper;
import com.kaijia.blog.domain.BlogCategory;
import com.kaijia.blog.service.IBlogCategoryService;

/**
 * 文章分类管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
@Service
public class BlogCategoryServiceImpl implements IBlogCategoryService 
{
    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    /**
     * 查询文章分类管理
     * 
     * @param id 文章分类管理主键
     * @return 文章分类管理
     */
    @Override
    public BlogCategory selectBlogCategoryById(Long id)
    {
        return blogCategoryMapper.selectBlogCategoryById(id);
    }

    /**
     * 查询文章分类管理列表
     * 
     * @param blogCategory 文章分类管理
     * @return 文章分类管理
     */
    @Override
    public List<BlogCategory> selectBlogCategoryList(BlogCategory blogCategory)
    {
        return blogCategoryMapper.selectBlogCategoryList(blogCategory);
    }

    /**
     * 新增文章分类管理
     * 
     * @param blogCategory 文章分类管理
     * @return 结果
     */
    @Override
    public int insertBlogCategory(BlogCategory blogCategory)
    {
        blogCategory.setCreateTime(DateUtils.getNowDate());
        return blogCategoryMapper.insertBlogCategory(blogCategory);
    }

    /**
     * 修改文章分类管理
     * 
     * @param blogCategory 文章分类管理
     * @return 结果
     */
    @Override
    public int updateBlogCategory(BlogCategory blogCategory)
    {
        return blogCategoryMapper.updateBlogCategory(blogCategory);
    }

    /**
     * 批量删除文章分类管理
     * 
     * @param ids 需要删除的文章分类管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogCategoryByIds(Long[] ids)
    {
        return blogCategoryMapper.deleteBlogCategoryByIds(ids);
    }

    /**
     * 删除文章分类管理信息
     * 
     * @param id 文章分类管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogCategoryById(Long id)
    {
        return blogCategoryMapper.deleteBlogCategoryById(id);
    }
}
