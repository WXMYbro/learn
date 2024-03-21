package mia.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import mia.infrastructure.dao.IAwardDao;
import mia.infrastructure.dao.IStrategyDao;
import mia.infrastructure.dao.IStrategyDetailDao;
import mia.infrastructure.vo.Award;
import mia.infrastructure.vo.Strategy;
import mia.infrastructure.vo.StrategyDetail;
import mia.strategy.model.aggregates.StrategyRich;
import mia.strategy.repository.IStrategyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Service
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {

       LambdaQueryWrapper<Strategy> strategyQuery = new LambdaQueryWrapper<Strategy>().eq(Strategy::getStrategyId, strategyId);

        Strategy strategy = strategyDao.getOne(strategyQuery);

        LambdaQueryWrapper<StrategyDetail> StrategyDetailQuery = new LambdaQueryWrapper<StrategyDetail>().eq(StrategyDetail::getStrategyId, strategyId);

        List<StrategyDetail> strategyDetailList = strategyDetailDao.list(StrategyDetailQuery);

        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {

        LambdaQueryWrapper<StrategyDetail> strategyDetailQuery = new LambdaQueryWrapper<StrategyDetail>()
                .eq(StrategyDetail::getStrategyId, strategyId)
                .eq(StrategyDetail::getAwardId, awardId)
                .gt(StrategyDetail::getAwardSurplusCount, 0);


        StrategyDetail one = strategyDetailDao.getOne(strategyDetailQuery);

        if (one == null){
            return false;
        }

        one.setAwardSurplusCount(one.getAwardSurplusCount()-1);

        return strategyDetailDao.updateById(one);
    }

}
