package com.kaijia.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.kaijia.blog.controller.vo.VArticleLike;
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
import com.kaijia.blog.domain.BlogArticleLike;
import com.kaijia.blog.service.IBlogArticleLikeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 点赞管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-21
 */
@RestController
@RequestMapping("/blog/articleLike")
public class BlogArticleLikeController extends BaseController
{
    @Autowired
    private IBlogArticleLikeService blogArticleLikeService;

    /**
     * 查询点赞管理列表
     */
    @PreAuthorize("@ss.hasPermi('blog:articleLike:list')")
    @GetMapping("/list")
    public TableDataInfo list(VArticleLike blogArticleLike)
    {
        startPage();
        List<VArticleLike> list = blogArticleLikeService.selectBlogArticleLikeList(blogArticleLike);
        return getDataTable(list);
    }

    /**
     * 导出点赞管理列表
     */
    @PreAuthorize("@ss.hasPermi('blog:articleLike:export')")
    @Log(title = "点赞管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VArticleLike blogArticleLike)
    {
        List<VArticleLike> list = blogArticleLikeService.selectBlogArticleLikeList(blogArticleLike);
        ExcelUtil<VArticleLike> util = new ExcelUtil<VArticleLike>(VArticleLike.class);
        util.exportExcel(response, list, "点赞管理数据");
    }

    /**
     * 获取点赞管理详细信息
     */
    @PreAuthorize("@ss.hasRole('user')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        return success(blogArticleLikeService.selectBlogArticleLikeByArticleId(articleId));
    }

    /**
     * 新增点赞管理
     */
    @PreAuthorize("@ss.hasRole('user')")
    @Log(title = "点赞管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogArticleLike blogArticleLike)
    {
        return toAjax(blogArticleLikeService.insertBlogArticleLike(blogArticleLike));
    }

    /**
     * 修改点赞管理
     */
    @PreAuthorize("@ss.hasPermi('blog:articleLike:edit')")
    @Log(title = "点赞管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogArticleLike blogArticleLike)
    {
        return toAjax(blogArticleLikeService.updateBlogArticleLike(blogArticleLike));
    }

    /**
     * 删除点赞管理
     */
    @PreAuthorize("@ss.hasPermi('blog:articleLike:remove')")
    @Log(title = "点赞管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(blogArticleLikeService.deleteBlogArticleLikeByArticleIds(articleIds));
    }

    /**
     * 用户取消点赞操作
     * @param articleId
     * @param userId
     * @return
     */
    @PreAuthorize("@ss.hasRole('user')")
    @DeleteMapping("/{articleId}/{userId}")
    public AjaxResult disLike(@PathVariable Long articleId, @PathVariable Long userId) {
        HashMap<String, Long> targetLike = new HashMap<>();
        targetLike.put("articleId", articleId);
        targetLike.put("userId", userId);
        return toAjax(blogArticleLikeService.deleteBlogArticleLikeByArticleIdAndUserId(targetLike));
    }
}