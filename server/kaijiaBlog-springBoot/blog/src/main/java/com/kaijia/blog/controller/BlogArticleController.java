package com.kaijia.blog.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaijia.blog.controller.vo.VArticle;
import com.kaijia.blog.domain.BlogVisitLog;
import com.kaijia.blog.service.IBlogVisitLogService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.kaijia.blog.domain.BlogArticle;
import com.kaijia.blog.service.IBlogArticleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-21
 */
@RestController
@RequestMapping("/blog/article")
public class BlogArticleController extends BaseController
{
    @Autowired
    private IBlogArticleService blogArticleService;
    @Autowired
    private IBlogVisitLogService blogVisitLogService;

    /**
     * 客户端查询文章
     */
    @Anonymous
    @GetMapping("getList4User")
    public TableDataInfo list4user(VArticle vArticle) {
        startPage();
        List<VArticle> list = blogArticleService.list4User(vArticle);
        return getDataTable(list);
    }

    /**
     * 查询所有文章管理列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(BlogArticle blogArticle)
    {
        startPage();
        List<BlogArticle> list = blogArticleService.selectBlogArticleList(blogArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章管理列表
     */
    @PreAuthorize("@ss.hasPermi('blog:article:export')")
    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogArticle blogArticle)
    {
        List<BlogArticle> list = blogArticleService.selectBlogArticleList(blogArticle);
        ExcelUtil<BlogArticle> util = new ExcelUtil<BlogArticle>(BlogArticle.class);
        util.exportExcel(response, list, "文章管理数据");
    }

    /**
     * 获取文章管理详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}/{userId}")
    @Transactional
    public AjaxResult getInfo(@PathVariable("id") Long id, @PathVariable Long userId)
    {
        BlogArticle blogArticle = blogArticleService.selectBlogArticleById(id);
        BlogArticle article = blogArticleService.updateViewCount(blogArticle);

        BlogVisitLog blogVisitLog = new BlogVisitLog();
//        获取ip
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = request.getHeader("X-Forwarded-FOr");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            ip = ip.split(",")[0];
        }

        blogVisitLog.setArticleId(id);
        blogVisitLog.setUserId(userId);
        blogVisitLog.setVisitedAt(DateUtils.getNowDate());
        blogVisitLog.setIpAddress(ip);
        blogVisitLogService.insertBlogVisitLog(blogVisitLog);

        article.setIsLike(blogArticleService.hasUserLikedArticle(article.getId(), userId));
        return success(article);
    }

    @PreAuthorize("@ss.hasRole('manager')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getArticleInfo(@PathVariable("articleId") Long articleId) {
        BlogArticle blogArticle = blogArticleService.selectBlogArticleById(articleId);
        return success(blogArticle);
    }

    /**
     * 新增文章管理
     */
    @PreAuthorize("@ss.hasRole('manager')")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogArticle blogArticle)
    {
        blogArticle.setAuthorId(SecurityUtils.getUserId());
        return toAjax(blogArticleService.insertBlogArticle(blogArticle));
    }

    /**
     * 修改文章管理
     */
    @PreAuthorize("@ss.hasPermi('blog:article:edit')")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogArticle blogArticle)
    {
        return toAjax(blogArticleService.updateBlogArticle(blogArticle));
    }

    /**
     * 删除文章管理
     */
    @PreAuthorize("@ss.hasPermi('blog:article:remove')")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogArticleService.deleteBlogArticleByIds(ids));
    }
}