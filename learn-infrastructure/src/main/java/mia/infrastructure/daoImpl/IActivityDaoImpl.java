package mia.infrastructure.daoImpl;

import mia.base.dao.BaseDaoImpl;
import mia.infrastructure.dao.IActivityDao;
import mia.infrastructure.mapper.IActivityMapper;
import mia.infrastructure.vo.Activity;
import org.springframework.stereotype.Service;

@Service
public class IActivityDaoImpl extends BaseDaoImpl<IActivityMapper, Activity> implements IActivityDao {

    @Override
    public void insert(Activity req) {

    }

    @Override
    public Activity queryActivityById(Long activityId) {
        return this.baseMapper.queryActivityById(activityId);
    }
}
