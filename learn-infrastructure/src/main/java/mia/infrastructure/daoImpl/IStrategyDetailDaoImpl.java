package mia.infrastructure.daoImpl;

import mia.base.dao.BaseDaoImpl;
import mia.infrastructure.dao.IStrategyDetailDao;
import mia.infrastructure.mapper.IStrategyDetailMapper;
import mia.infrastructure.vo.StrategyDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IStrategyDetailDaoImpl extends BaseDaoImpl<IStrategyDetailMapper, StrategyDetail> implements IStrategyDetailDao {
    @Override
    public List<StrategyDetail> queryStrategyDetailList(Long strategyId) {
        return this.baseMapper.queryStrategyDetailList(strategyId);
    }

    @Override
    public int deductStock(StrategyDetail strategyDetailReq) {
        return this.baseMapper.deductStock(strategyDetailReq);
    }
}
