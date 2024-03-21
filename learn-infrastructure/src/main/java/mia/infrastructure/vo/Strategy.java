package mia.infrastructure.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 * <p>
 * 策略配置
 */
@Getter
@Setter
@TableName("strategy")
public class Strategy implements Serializable {

    private static final long serialVersionUID = 1L;

    // 自增ID
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    // 策略ID
    private Long strategyId;

    // 策略描述
    private String strategyDesc;

    // 策略方式「1:单项概率、2:总体概率」
    private Integer strategyMode;

    // 发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」
    private Integer grantType;

    // 发放奖品时间
    private Date grantDate;

    // 扩展信息
    private String extInfo;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;
}
