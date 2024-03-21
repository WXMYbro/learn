package mia.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mia.infrastructure.vo.Award;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAwardMapper extends BaseMapper<Award> {

    Award queryAwardInfo(String awardId);
}
