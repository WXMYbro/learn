package mia.strategy.model.res;

import lombok.Getter;
import lombok.Setter;
import mia.common.Constants;
import mia.strategy.model.vo.DrawAwardInfoVO;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Getter
@Setter
public class DrawResult {

    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;

    // 奖品ID
    private Integer drawState = Constants.DrawState.FAIL.getCode();

    // 奖品名称
    private DrawAwardInfoVO DrawAwardInfoVO;

    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public DrawResult(String uId, Long strategyId, Integer drawState, DrawAwardInfoVO DrawAwardInfoVO) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.DrawAwardInfoVO = DrawAwardInfoVO;
    }

}
