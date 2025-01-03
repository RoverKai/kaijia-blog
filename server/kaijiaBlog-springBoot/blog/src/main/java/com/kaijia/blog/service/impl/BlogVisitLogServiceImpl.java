package com.kaijia.blog.service.impl;

import java.util.List;

import com.kaijia.blog.controller.vo.VArticleVisitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaijia.blog.mapper.BlogVisitLogMapper;
import com.kaijia.blog.domain.BlogVisitLog;
import com.kaijia.blog.service.IBlogVisitLogService;

/**
 * 浏览记录Service业务层处理
 * 
 * @author kaijia
 * @date 2024-10-22
 */
@Service
public class BlogVisitLogServiceImpl implements IBlogVisitLogService 
{
    @Autowired
    private BlogVisitLogMapper blogVisitLogMapper;

    /**
     * 查询浏览记录
     * 
     * @param id 浏览记录主键
     * @return 浏览记录
     */
    @Override
    public BlogVisitLog selectBlogVisitLogById(Long id)
    {
        return blogVisitLogMapper.selectBlogVisitLogById(id);
    }

    /**
     * 查询浏览记录列表
     * 
     * @param blogVisitLog 浏览记录
     * @return 浏览记录
     */
    @Override
    public List<VArticleVisitLog> selectBlogVisitLogList(VArticleVisitLog blogVisitLog)
    {
        return blogVisitLogMapper.selectBlogVisitLogList(blogVisitLog);
    }

    /**
     * 新增浏览记录
     * 
     * @param blogVisitLog 浏览记录
     * @return 结果
     */
    @Override
    public int insertBlogVisitLog(BlogVisitLog blogVisitLog)
    {
        return blogVisitLogMapper.insertBlogVisitLog(blogVisitLog);
    }

    /**
     * 修改浏览记录
     * 
     * @param blogVisitLog 浏览记录
     * @return 结果
     */
    @Override
    public int updateBlogVisitLog(BlogVisitLog blogVisitLog)
    {
        return blogVisitLogMapper.updateBlogVisitLog(blogVisitLog);
    }

    /**
     * 批量删除浏览记录
     * 
     * @param ids 需要删除的浏览记录主键
     * @return 结果
     */
    @Override
    public int deleteBlogVisitLogByIds(Long[] ids)
    {
        return blogVisitLogMapper.deleteBlogVisitLogByIds(ids);
    }

    /**
     * 删除浏览记录信息
     * 
     * @param id 浏览记录主键
     * @return 结果
     */
    @Override
    public int deleteBlogVisitLogById(Long id)
    {
        return blogVisitLogMapper.deleteBlogVisitLogById(id);
    }
}