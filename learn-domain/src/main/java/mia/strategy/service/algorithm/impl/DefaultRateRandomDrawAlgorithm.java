package mia.strategy.service.algorithm.impl;

import mia.strategy.model.vo.AwardRateInfoVO;
import mia.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * 必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围
 */
@Service
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        BigDecimal differenceDenominator = BigDecimal.ZERO;

        // 排除掉不在抽奖范围的奖品ID集合
        ArrayList<AwardRateInfoVO> differenceAwardRateList = new ArrayList<>();
        List<AwardRateInfoVO> awardRateIntervalValList = AwardRateInfoVOMap.get(strategyId);
        for (AwardRateInfoVO AwardRateInfoVO:awardRateIntervalValList){
            String awardId = AwardRateInfoVO.getAwardId();
            if (excludeAwardIds.contains(awardId)){
                continue;
            }
            differenceAwardRateList.add(AwardRateInfoVO);
            differenceDenominator = differenceDenominator.add(AwardRateInfoVO.getAwardRate());
        }

        // 前置判断
        if (differenceAwardRateList.size() == 0){
            return null;
        }
        if (differenceAwardRateList.size() == 1){
            differenceAwardRateList.get(0).getAwardId();
        }

        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        // 循环获取奖品
        String awardId = "";
        int cursorVal = 0;

        for (AwardRateInfoVO AwardRateInfoVO : differenceAwardRateList){
            int rateVal = AwardRateInfoVO.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if (randomVal<=(cursorVal+rateVal)){
                awardId = AwardRateInfoVO.getAwardId();
                break;
            }
            cursorVal+= rateVal;
        }


        return awardId;
    }
}
