package com.kaijia.blog.mapper;

import java.util.List;
import com.kaijia.blog.domain.BlogCategory;

/**
 * 文章分类管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
public interface BlogCategoryMapper 
{
    /**
     * 查询文章分类管理
     * 
     * @param id 文章分类管理主键
     * @return 文章分类管理
     */
    public BlogCategory selectBlogCategoryById(Long id);

    /**
     * 查询文章分类管理列表
     * 
     * @param blogCategory 文章分类管理
     * @return 文章分类管理集合
     */
    public List<BlogCategory> selectBlogCategoryList(BlogCategory blogCategory);

    /**
     * 新增文章分类管理
     * 
     * @param blogCategory 文章分类管理
     * @return 结果
     */
    public int insertBlogCategory(BlogCategory blogCategory);

    /**
     * 修改文章分类管理
     * 
     * @param blogCategory 文章分类管理
     * @return 结果
     */
    public int updateBlogCategory(BlogCategory blogCategory);

    /**
     * 删除文章分类管理
     * 
     * @param id 文章分类管理主键
     * @return 结果
     */
    public int deleteBlogCategoryById(Long id);

    /**
     * 批量删除文章分类管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogCategoryByIds(Long[] ids);
}
