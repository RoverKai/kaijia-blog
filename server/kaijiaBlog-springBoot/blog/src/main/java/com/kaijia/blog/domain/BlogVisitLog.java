package com.kaijia.blog.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 浏览记录对象 blog_visit_log
 * 
 * @author kaijia
 * @date 2024-10-22
 */
public class BlogVisitLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 文章ID */
    @Excel(name = "文章ID")
    private Long articleId;

    /** 用户ID，关联sys_user表 */
    @Excel(name = "用户ID，关联sys_user表")
    private Long userId;

    /** 访问者IP地址 */
    @Excel(name = "访问者IP地址")
    private String ipAddress;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date visitedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
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
    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
    }
    public void setVisitedAt(Date visitedAt) 
    {
        this.visitedAt = visitedAt;
    }

    public Date getVisitedAt() 
    {
        return visitedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("articleId", getArticleId())
            .append("userId", getUserId())
            .append("ipAddress", getIpAddress())
            .append("visitedAt", getVisitedAt())
            .toString();
    }
}