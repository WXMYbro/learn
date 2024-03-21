package mia.strategy.service.draw.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import mia.infrastructure.dao.IStrategyDetailDao;
import mia.infrastructure.vo.StrategyDetail;
import mia.strategy.repository.IStrategyRepository;
import mia.strategy.service.algorithm.IDrawAlgorithm;
import mia.strategy.service.draw.AbstractDrawBase;
import mia.strategy.service.draw.DrawBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;




@Service
public class DrawExecImpl extends AbstractDrawBase {

    @Resource
    private IStrategyRepository strategyRepository;

    @Resource
    private DrawBase drawBase;

    @Resource
    private IStrategyDetailDao iStrategyDetailDao;

    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {

        LambdaQueryWrapper<StrategyDetail> queryExcludeAwardIds = new LambdaQueryWrapper<StrategyDetail>()
                .eq(StrategyDetail::getStrategyId, strategyId)
                .eq(StrategyDetail::getAwardSurplusCount,0);

        
        List<StrategyDetail> strategyDetail = iStrategyDetailDao.list(queryExcludeAwardIds);

        ArrayList<String> awardIdList = new ArrayList<>();

        for (StrategyDetail strategyDetails:strategyDetail){
            awardIdList.add(strategyDetails.getAwardId());
        }

        return awardIdList;
    }

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds){

        // 执行抽奖
        String awardId = drawAlgorithm.randomDraw(strategyId, excludeAwardIds);

        // 判断抽奖结果
        if (null == awardId) {
            return null;
        }

        /*
         * 扣减库存，暂时采用数据库行级锁的方式进行扣减库存，后续优化为 Redis 分布式锁扣减 decr/incr
         * 注意：通常数据库直接锁行记录的方式并不能支撑较大体量的并发，但此种方式需要了解，因为在分库分表下的正常数据流量下的个人数据记录中，是可以使用行级锁的，因为他只影响到自己的记录，不会影响到其他人
         */
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);

        // 返回结果，库存扣减成功返回奖品ID，否则返回NULL 「在实际的业务场景中，如果中奖奖品库存为空，则会发送兜底奖品，比如各类券」
        return isSuccess ? awardId : null;

    }
}
