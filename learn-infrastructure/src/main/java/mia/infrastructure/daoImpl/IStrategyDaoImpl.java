package mia.infrastructure.daoImpl;

import mia.base.dao.BaseDaoImpl;
import mia.infrastructure.dao.IStrategyDao;
import mia.infrastructure.mapper.IStrategyMapper;
import mia.infrastructure.vo.Strategy;
import org.springframework.stereotype.Service;

@Service
public class IStrategyDaoImpl extends BaseDaoImpl<IStrategyMapper, Strategy> implements IStrategyDao {
    @Override
    public Strategy queryStrategy(Long strategyId) {
        return this.baseMapper.queryStrategy(strategyId);
    }
}
