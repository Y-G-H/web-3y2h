package cn.y3h2.blog.web.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageReq implements Serializable {
    private static final long serialVersionUID = -6297698576111057501L;

    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 页码
     */
    private Integer pageNo;
}