package com.kaijia.blog.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;


@Data
public class BlogDanmu extends BaseEntity {
    private Long id;
    private Long userId;
    private String content;
}
