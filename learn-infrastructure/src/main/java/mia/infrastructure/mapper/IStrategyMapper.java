package mia.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mia.infrastructure.vo.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyMapper extends BaseMapper<Strategy> {

    Strategy queryStrategy(Long strategyId);
}
