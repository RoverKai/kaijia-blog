package com.kaijia.blog.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.kaijia.blog.domain.BlogVisitLog;
import com.kaijia.blog.service.IBlogVisitLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 浏览记录Controller
 * 
 * @author kaijia
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/blog/log")
public class BlogVisitLogController extends BaseController
{
    @Autowired
    private IBlogVisitLogService blogVisitLogService;

    /**
     * 查询浏览记录列表
     */
    @PreAuthorize("@ss.hasPermi('blog:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogVisitLog blogVisitLog)
    {
        startPage();
        List<BlogVisitLog> list = blogVisitLogService.selectBlogVisitLogList(blogVisitLog);
        return getDataTable(list);
    }

    /**
     * 导出浏览记录列表
     */
    @PreAuthorize("@ss.hasPermi('blog:log:export')")
    @Log(title = "浏览记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogVisitLog blogVisitLog)
    {
        List<BlogVisitLog> list = blogVisitLogService.selectBlogVisitLogList(blogVisitLog);
        ExcelUtil<BlogVisitLog> util = new ExcelUtil<BlogVisitLog>(BlogVisitLog.class);
        util.exportExcel(response, list, "浏览记录数据");
    }

    /**
     * 获取浏览记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blogVisitLogService.selectBlogVisitLogById(id));
    }

    /**
     * 新增浏览记录
     */
    @PreAuthorize("@ss.hasPermi('blog:log:add')")
    @Log(title = "浏览记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogVisitLog blogVisitLog)
    {
        return toAjax(blogVisitLogService.insertBlogVisitLog(blogVisitLog));
    }

    /**
     * 修改浏览记录
     */
    @PreAuthorize("@ss.hasPermi('blog:log:edit')")
    @Log(title = "浏览记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogVisitLog blogVisitLog)
    {
        return toAjax(blogVisitLogService.updateBlogVisitLog(blogVisitLog));
    }

    /**
     * 删除浏览记录
     */
    @PreAuthorize("@ss.hasPermi('blog:log:remove')")
    @Log(title = "浏览记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogVisitLogService.deleteBlogVisitLogByIds(ids));
    }
}