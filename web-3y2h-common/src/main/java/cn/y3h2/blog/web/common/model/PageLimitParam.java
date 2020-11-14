package cn.y3h2.blog.web.common.model;

import lombok.Data;

@Data
/**
* @ClassName PageLimitParam
* @Author kongming
* @Date 2020/11/8 5:21 下午
* @Description 分页入参
*/
public class PageLimitParam {

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 页长
     */
    private Integer pageSize;
}
