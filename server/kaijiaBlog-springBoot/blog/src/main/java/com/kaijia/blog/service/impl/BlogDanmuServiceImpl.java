package com.kaijia.blog.service.impl;

import com.kaijia.blog.domain.BlogDanmu;
import com.kaijia.blog.mapper.BlogDanmuMapper;
import com.kaijia.blog.service.IBlogDanmuService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BlogDanmuServiceImpl implements IBlogDanmuService {
    @Autowired
    private BlogDanmuMapper blogDanmuMapper;

    @Override
    public List<BlogDanmu> getDanmuList(BlogDanmu blogDanmu) {
        return blogDanmuMapper.getDanmuList(blogDanmu);
    }
    @Override
    public int insertDanmu(BlogDanmu blogDanmu) {
        blogDanmu.setCreateTime(DateUtils.getNowDate());
        return blogDanmuMapper.insertDanmu(blogDanmu);
    }
}
