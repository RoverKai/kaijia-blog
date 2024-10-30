package com.kaijia.blog.mapper;

import com.kaijia.blog.domain.BlogDanmu;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

public interface BlogDanmuMapper {
    public List<BlogDanmu> getDanmuList(BlogDanmu blogDanmu);

    public int insertDanmu(BlogDanmu blogDanmu);
}
