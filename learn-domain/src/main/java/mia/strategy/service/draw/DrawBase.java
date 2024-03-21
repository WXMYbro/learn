package mia.strategy.service.draw;

import mia.infrastructure.vo.StrategyDetail;
import mia.strategy.model.vo.AwardRateInfoVO;
import mia.strategy.service.algorithm.IDrawAlgorithm;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class DrawBase extends DrawConfig{

    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {
        if (1 != strategyMode){
            return;
        }
        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);

        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if(existRateTuple){
            return;
        }

        List<AwardRateInfoVO> AwardRateInfoVOList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetail strategyDetail : strategyDetailList) {
            AwardRateInfoVOList.add(new AwardRateInfoVO(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, AwardRateInfoVOList);

    }
}
