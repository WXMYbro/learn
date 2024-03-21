package mia.infrastructure.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserTakeActivity implements Serializable {

    /**
     * 自增ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动领取ID
     */
    private Long takeId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动领取时间
     */
    private Date takeDate;
    /**
     * 领取次数
     */
    private Integer takeCount;
    /**
     * 防重ID
     */
    private String uuid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
