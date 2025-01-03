package com.kaijia.blog.controller.vo;

import com.kaijia.blog.domain.BlogVisitLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VArticleVisitLog extends BlogVisitLog {
    private String userName;
    private String articleTitle;
}
