package com.kaijia.blog.controller.vo;

import lombok.Data;

import java.util.Date;

@Data
public class VArticle {
    private Long id;
    private String title;
    private String content;
    private Date createTime;
    private Long categoryId;
    private Integer viewCount;
    private Integer likeCount;
}
