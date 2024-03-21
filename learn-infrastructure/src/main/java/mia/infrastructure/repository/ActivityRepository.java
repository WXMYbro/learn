package mia.infrastructure.repository;

import mia.activity.model.vo.ActivityVO;
import mia.activity.model.vo.AwardVO;
import mia.activity.model.vo.StrategyDetailVO;
import mia.activity.model.vo.StrategyVO;
import mia.activity.repository.IActivityRepository;
import mia.common.BaseConvertUtil;
import mia.common.Constants;
import mia.common.ResultEnum;
import mia.common.ResultException;
import mia.infrastructure.dao.IActivityDao;
import mia.infrastructure.dao.IAwardDao;
import mia.infrastructure.dao.IStrategyDao;
import mia.infrastructure.dao.IStrategyDetailDao;
import mia.infrastructure.vo.Activity;
import mia.infrastructure.vo.Award;
import mia.infrastructure.vo.Strategy;
import mia.infrastructure.vo.StrategyDetail;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.List;

public class ActivityRepository implements IActivityRepository {

    @Resource
    IActivityDao activityDao;

    @Resource
    IAwardDao awardDao;

    @Resource
    IStrategyDao strategyDao;

    @Resource
    IStrategyDetailDao strategyDetailDao;


    @Override
    public void addActivity(ActivityVO activity) {

        Activity activityOne = new Activity();
        BeanUtils.copyProperties(activity, activityOne);

        boolean save = activityDao.save(activityOne);

        if (!save) {
            throw new ResultException(ResultEnum.ERROR,"添加失败，请刷新重试！");

        }

    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> awards = BaseConvertUtil.listConver(awardList, Award.class);

        boolean saves = awardDao.saveOrUpdateBatch(awards);

        if (!saves) {
            throw new ResultException(ResultEnum.ERROR,"添加失败，请刷新重试！");

        }
    }

    @Override
    public void addStrategy(StrategyVO strategy) {

        Strategy strategyOne = new Strategy();
        BeanUtils.copyProperties(strategy, strategyOne);

        boolean save = strategyDao.save(strategyOne);

        if (!save) {
            throw new ResultException(ResultEnum.ERROR,"添加失败，请刷新重试！");

        }

    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {

        List<StrategyDetail> strategyDetails = BaseConvertUtil.listConver(strategyDetailList, StrategyDetail.class);

        boolean saves = strategyDetailDao.saveOrUpdateBatch(strategyDetails);

        if (!saves) {
            throw new ResultException(ResultEnum.ERROR,"添加失败，请刷新重试！");

        }

    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        return false;
    }


}
