package mia.infrastructure.daoImpl;


import mia.base.dao.BaseDaoImpl;
import mia.infrastructure.dao.IUserTakeActivityDao;
import mia.infrastructure.mapper.IUserTakeActivityMapper;
import mia.infrastructure.vo.UserTakeActivity;
import org.springframework.stereotype.Service;

@Service
public class IUserTakeActivityDaoImpl extends BaseDaoImpl<IUserTakeActivityMapper, UserTakeActivity> implements IUserTakeActivityDao {
}
