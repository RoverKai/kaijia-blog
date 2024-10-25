package com.kaijia.blog.controller.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleView {
    private String title;
    private Date createTime;
    private Long categoryId;
    private Integer viewCount;
}
