package mia.strategy.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 奖品概率信息，奖品编号、库存、概率
 */
@Data
public class AwardRateInfoVO {

    // 奖品ID
    private String awardId;

    // 中奖概率
    private BigDecimal awardRate;

    public AwardRateInfoVO() {
    }

    public AwardRateInfoVO(String awardId, BigDecimal awardRate) {
        this.awardId = awardId;
        this.awardRate = awardRate;
    }

}
