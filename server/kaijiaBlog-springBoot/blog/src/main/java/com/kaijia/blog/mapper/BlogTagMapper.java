package com.kaijia.blog.mapper;

import java.util.List;
import com.kaijia.blog.domain.BlogTag;

/**
 * 标签管理Mapper接口
 * 
 * @author kaijia
 * @date 2024-10-22
 */
public interface BlogTagMapper 
{
    /**
     * 查询标签管理
     * 
     * @param id 标签管理主键
     * @return 标签管理
     */
    public BlogTag selectBlogTagById(Long id);

    /**
     * 查询标签管理列表
     * 
     * @param blogTag 标签管理
     * @return 标签管理集合
     */
    public List<BlogTag> selectBlogTagList(BlogTag blogTag);

    /**
     * 新增标签管理
     * 
     * @param blogTag 标签管理
     * @return 结果
     */
    public int insertBlogTag(BlogTag blogTag);

    /**
     * 修改标签管理
     * 
     * @param blogTag 标签管理
     * @return 结果
     */
    public int updateBlogTag(BlogTag blogTag);

    /**
     * 删除标签管理
     * 
     * @param id 标签管理主键
     * @return 结果
     */
    public int deleteBlogTagById(Long id);

    /**
     * 批量删除标签管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogTagByIds(Long[] ids);
}
