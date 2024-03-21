package mia.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import mia.infrastructure.vo.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyDetailMapper extends BaseMapper<StrategyDetail> {

    int deductStock(StrategyDetail strategyDetailReq);

    List<StrategyDetail> queryStrategyDetailList(Long strategyId);
}
