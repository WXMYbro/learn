package mia.infrastructure.daoImpl;

import mia.base.dao.BaseDaoImpl;
import mia.infrastructure.dao.IAwardDao;
import mia.infrastructure.mapper.IAwardMapper;
import mia.infrastructure.vo.Award;
import org.springframework.stereotype.Service;

@Service
public class IAwardDaoImpl extends BaseDaoImpl<IAwardMapper, Award> implements IAwardDao {
    @Override
    public Award queryAwardInfo(String awardId) {
        return this.baseMapper.queryAwardInfo(awardId);
    }
}
