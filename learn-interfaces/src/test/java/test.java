import com.alibaba.fastjson.JSON;
import mia.MainApplication;
import mia.award.model.req.GoodsReq;
import mia.award.model.res.DistributionRes;
import mia.award.service.factory.DistributionGoodsFactory;
import mia.award.service.goods.IDistributionGoods;
import mia.common.Constants;
import mia.strategy.model.req.DrawReq;
import mia.strategy.model.res.DrawResult;
import mia.strategy.model.vo.DrawAwardInfoVO;
import mia.strategy.service.draw.IDrawExec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class test {

    private Logger logger = LoggerFactory.getLogger(test.class);

    @Resource
    IDrawExec IDrawExec;

    @Resource
    DistributionGoodsFactory distributionGoodsFactory;


    @Test
    public void test_drawExec() {

        DrawResult drawResult = IDrawExec.doDrawExec(new DrawReq("八杯水", 10001L));

        logger.info("测试结果：{}", JSON.toJSONString(drawResult));
    }

    @Test
    public void test_award() {
        // 执行抽奖
        DrawResult drawResult = IDrawExec.doDrawExec(new DrawReq("小傅哥", 10001L));

        // 判断抽奖结果
        Integer drawState = drawResult.getDrawState();

        if (Constants.DrawState.FAIL.getCode().equals(drawState)) {
            return;
        }

        // 封装发奖参数，orderId：2109313442431 为模拟ID，需要在用户参与领奖活动时生成
        DrawAwardInfoVO DrawAwardInfoVO = drawResult.getDrawAwardInfoVO();
        GoodsReq goodsReq = new GoodsReq(drawResult.getUId(), "2109313442431", DrawAwardInfoVO.getAwardId(), DrawAwardInfoVO.getAwardName(), DrawAwardInfoVO.getAwardContent());

        // 根据 awardType 从抽奖工厂中获取对应的发奖服务
        IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(DrawAwardInfoVO.getAwardType());
        DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);


        logger.info("测试结果：{}", JSON.toJSONString(distributionRes));

        System.out.println("----------------------");
        System.out.println(distributionRes);
        System.out.println("----------------------");
    }

}

