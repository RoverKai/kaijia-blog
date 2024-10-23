package com.kaijia.blog.service;

import java.util.List;
import com.kaijia.blog.domain.BlogVisitLog;

/**
 * 浏览记录Service接口
 * 
 * @author kaijia
 * @date 2024-10-22
 */
public interface IBlogVisitLogService 
{
    /**
     * 查询浏览记录
     * 
     * @param id 浏览记录主键
     * @return 浏览记录
     */
    public BlogVisitLog selectBlogVisitLogById(Long id);

    /**
     * 查询浏览记录列表
     * 
     * @param blogVisitLog 浏览记录
     * @return 浏览记录集合
     */
    public List<BlogVisitLog> selectBlogVisitLogList(BlogVisitLog blogVisitLog);

    /**
     * 新增浏览记录
     * 
     * @param blogVisitLog 浏览记录
     * @return 结果
     */
    public int insertBlogVisitLog(BlogVisitLog blogVisitLog);

    /**
     * 修改浏览记录
     * 
     * @param blogVisitLog 浏览记录
     * @return 结果
     */
    public int updateBlogVisitLog(BlogVisitLog blogVisitLog);

    /**
     * 批量删除浏览记录
     * 
     * @param ids 需要删除的浏览记录主键集合
     * @return 结果
     */
    public int deleteBlogVisitLogByIds(Long[] ids);

    /**
     * 删除浏览记录信息
     * 
     * @param id 浏览记录主键
     * @return 结果
     */
    public int deleteBlogVisitLogById(Long id);
}