package com.kaijia.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 点赞管理对象 blog_article_like
 * 
 * @author ruoyi
 * @date 2024-10-21
 */
public class BlogArticleLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    @Excel(name = "文章ID")
    private Long articleId;

    /** 用户ID，关联sys_user表 */
    @Excel(name = "用户ID，关联sys_user表")
    private Long userId;

    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}