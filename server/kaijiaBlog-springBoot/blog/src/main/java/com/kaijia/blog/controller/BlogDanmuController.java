package com.kaijia.blog.controller;


import com.kaijia.blog.domain.BlogDanmu;
import com.kaijia.blog.service.IBlogDanmuService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog/danmu")
@Anonymous
public class BlogDanmuController extends BaseController {
    @Autowired
    IBlogDanmuService blogDanmuService;
    @GetMapping("list")
    @Anonymous
    public List<BlogDanmu> getDanmuList(BlogDanmu blogDanmu) {
        return blogDanmuService.getDanmuList(blogDanmu);
    }
    @PreAuthorize("@ss.hasRole('user')")
    @PostMapping
    public AjaxResult receiveDanmu(@RequestBody BlogDanmu blogDanmu) {
        return toAjax(blogDanmuService.insertDanmu(blogDanmu));
    }

}
