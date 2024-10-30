package com.kaijia.blog.controller.vo;

import com.kaijia.blog.domain.BlogComment;
import lombok.Data;

@Data
public class VComment extends BlogComment {

    private String userName;
}
