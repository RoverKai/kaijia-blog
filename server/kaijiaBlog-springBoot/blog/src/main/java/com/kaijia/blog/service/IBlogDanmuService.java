package com.kaijia.blog.service;

import com.kaijia.blog.domain.BlogDanmu;

import java.util.List;

public interface IBlogDanmuService
{
    public List<BlogDanmu> getDanmuList(BlogDanmu blogDanmu);
    public int insertDanmu(BlogDanmu blogDanmu);
}
