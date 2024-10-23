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
import com.kaijia.blog.domain.BlogTag;
import com.kaijia.blog.service.IBlogTagService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标签管理Controller
 * 
 * @author kaijia
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController extends BaseController
{
    @Autowired
    private IBlogTagService blogTagService;

    /**
     * 查询标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogTag blogTag)
    {
        startPage();
        List<BlogTag> list = blogTagService.selectBlogTagList(blogTag);
        return getDataTable(list);
    }

    /**
     * 导出标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:export')")
    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogTag blogTag)
    {
        List<BlogTag> list = blogTagService.selectBlogTagList(blogTag);
        ExcelUtil<BlogTag> util = new ExcelUtil<BlogTag>(BlogTag.class);
        util.exportExcel(response, list, "标签管理数据");
    }

    /**
     * 获取标签管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blogTagService.selectBlogTagById(id));
    }

    /**
     * 新增标签管理
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:add')")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogTag blogTag)
    {
        return toAjax(blogTagService.insertBlogTag(blogTag));
    }

    /**
     * 修改标签管理
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:edit')")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogTag blogTag)
    {
        return toAjax(blogTagService.updateBlogTag(blogTag));
    }

    /**
     * 删除标签管理
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:remove')")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogTagService.deleteBlogTagByIds(ids));
    }
}