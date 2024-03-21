package mia.strategy.service.draw;

import mia.common.Constants;
import mia.infrastructure.vo.Award;
import mia.infrastructure.vo.Strategy;
import mia.infrastructure.vo.StrategyDetail;
import mia.strategy.model.aggregates.StrategyRich;
import mia.strategy.model.req.DrawReq;
import mia.strategy.model.res.DrawResult;
import mia.strategy.model.vo.AwardRateInfoVO;
import mia.strategy.model.vo.DrawAwardInfoVO;
import mia.strategy.service.algorithm.IDrawAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DrawResult doDrawExec(DrawReq req) {

        // 1. 获取抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();

        // 2. 校验抽奖策略是否已经初始化到内存
        this.checkAndInitRateData(req.getStrategyId(),strategy.getStrategyMode(),strategyRich.getStrategyDetailList());

        // 3. 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        // 4. 执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(),drawAlgorithmGroup.get(strategy.getStrategyMode()), excludeAwardIds);

        // 5. 包装中奖结果
        return buildDrawResult(req.getUId(), req.getStrategyId(), awardId);
    }

    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等，这类数据是含有业务逻辑的，所以需要由具体的实现方决定
     *
     * @param strategyId 策略ID
     * @return 排除的奖品ID集合
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 执行抽奖算法
     *
     * @param strategyId      策略ID
     * @param drawAlgorithm   抽奖算法模型
     * @param excludeAwardIds 排除的抽奖ID集合
     * @return 中奖奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    /**
     * 校验抽奖策略是否已经初始化到内存
     *
     * @param strategyId         抽奖策略ID
     * @param strategyMode       抽奖策略模式
     * @param strategyDetailList 抽奖策略详情
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {

        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);

        // 已初始化过的数据，不必重复初始化
        if (drawAlgorithm.isExistRateTuple(strategyId)){
            return;
        }

        // 解析并初始化中奖概率数据到散列表
        List<AwardRateInfoVO> AwardRateInfoVOList = new ArrayList<>(strategyDetailList.size());

        for (StrategyDetail strategyDetail: strategyDetailList){
            AwardRateInfoVOList.add(new AwardRateInfoVO(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId,AwardRateInfoVOList);
    }

    /**
     * 包装抽奖结果
     *
     * @param uId        用户ID
     * @param strategyId 策略ID
     * @param awardId    奖品ID，null 情况：并发抽奖情况下，库存临界值1 -> 0，会有用户中奖结果为 null
     * @return 中奖结果
     */
    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId) {

        if (null == awardId) {
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        Award award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardInfoVO DrawAwardInfoVO = new DrawAwardInfoVO(award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), DrawAwardInfoVO);
    }
}
