package com.kaijia.blog.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaijia.blog.mapper.BlogTagMapper;
import com.kaijia.blog.domain.BlogTag;
import com.kaijia.blog.service.IBlogTagService;

/**
 * 标签管理Service业务层处理
 * 
 * @author kaijia
 * @date 2024-10-22
 */
@Service
public class BlogTagServiceImpl implements IBlogTagService 
{
    @Autowired
    private BlogTagMapper blogTagMapper;

    /**
     * 查询标签管理
     * 
     * @param id 标签管理主键
     * @return 标签管理
     */
    @Override
    public BlogTag selectBlogTagById(Long id)
    {
        return blogTagMapper.selectBlogTagById(id);
    }

    /**
     * 查询标签管理列表
     * 
     * @param blogTag 标签管理
     * @return 标签管理
     */
    @Override
    public List<BlogTag> selectBlogTagList(BlogTag blogTag)
    {
        return blogTagMapper.selectBlogTagList(blogTag);
    }

    /**
     * 新增标签管理
     * 
     * @param blogTag 标签管理
     * @return 结果
     */
    @Override
    public int insertBlogTag(BlogTag blogTag)
    {
        return blogTagMapper.insertBlogTag(blogTag);
    }

    /**
     * 修改标签管理
     * 
     * @param blogTag 标签管理
     * @return 结果
     */
    @Override
    public int updateBlogTag(BlogTag blogTag)
    {
        return blogTagMapper.updateBlogTag(blogTag);
    }

    /**
     * 批量删除标签管理
     * 
     * @param ids 需要删除的标签管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogTagByIds(Long[] ids)
    {
        return blogTagMapper.deleteBlogTagByIds(ids);
    }

    /**
     * 删除标签管理信息
     * 
     * @param id 标签管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogTagById(Long id)
    {
        return blogTagMapper.deleteBlogTagById(id);
    }
}
