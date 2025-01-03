package com.kaijia.blog.mapper;

import java.util.List;

import com.kaijia.blog.controller.vo.VArticleVisitLog;
import com.kaijia.blog.domain.BlogVisitLog;

/**
 * 浏览记录Mapper接口
 * 
 * @author kaijia
 * @date 2024-10-22
 */
public interface BlogVisitLogMapper 
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
    public List<VArticleVisitLog> selectBlogVisitLogList(VArticleVisitLog blogVisitLog);

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
     * 删除浏览记录
     * 
     * @param id 浏览记录主键
     * @return 结果
     */
    public int deleteBlogVisitLogById(Long id);

    /**
     * 批量删除浏览记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogVisitLogByIds(Long[] ids);
}