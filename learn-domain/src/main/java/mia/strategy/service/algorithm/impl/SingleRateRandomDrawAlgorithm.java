package mia.strategy.service.algorithm.impl;

import mia.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
/**
 * 单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 */
@Service
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds){

        String[] rateTuple = super.rateTupleMap.get(strategyId);

        assert rateTuple!=null;

        int randomVal =new SecureRandom().nextInt(100)+1;
        int idx = super.hashIdx(randomVal);

        String awardId = rateTuple[idx];
        if (excludeAwardIds.contains(awardId)){
            return "未中奖";
        }
        return awardId;
    }
}
