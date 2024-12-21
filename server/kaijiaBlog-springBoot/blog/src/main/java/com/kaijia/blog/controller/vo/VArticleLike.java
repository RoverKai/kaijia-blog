package com.kaijia.blog.controller.vo;

import com.kaijia.blog.domain.BlogArticleLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Kai
 * @Time: 2024/12/21 12:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VArticleLike extends BlogArticleLike {
    private String userName;
    private String articleTitle;
}
