package mia.activity.service.deploy.impl;

import mia.activity.model.aggregates.ActivityConfigRich;
import mia.activity.model.req.ActivityConfigReq;
import mia.activity.model.vo.ActivityVO;
import mia.activity.model.vo.AwardVO;
import mia.activity.model.vo.StrategyDetailVO;
import mia.activity.model.vo.StrategyVO;
import mia.activity.repository.IActivityRepository;
import mia.activity.service.deploy.IActivityDeploy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IActivityDeployImpl implements IActivityDeploy {

    @Resource
    IActivityRepository activityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {

        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

        try {
            // 添加活动配置
            ActivityVO activity = activityConfigRich.getActivity();
            activityRepository.addActivity(activity);

            // 添加奖品配置
            List<AwardVO> awardList = activityConfigRich.getAwardList();
            activityRepository.addAward(awardList);

            // 添加策略配置
            StrategyVO strategy = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategy);

            // 添加策略明细配置
            List<StrategyDetailVO> strategyDetailList = activityConfigRich.getStrategy().getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateActivity(ActivityConfigReq req) {

    }
}
