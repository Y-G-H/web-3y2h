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

    private Integer limit;

    private Integer offset;
}
