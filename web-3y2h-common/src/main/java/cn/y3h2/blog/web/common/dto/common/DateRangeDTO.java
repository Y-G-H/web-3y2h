package cn.y3h2.blog.web.common.dto.common;

import lombok.Data;

import java.util.Date;

@Data
public class DateRangeDTO {

    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;

}
