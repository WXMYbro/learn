package mia.infrastructure.dao;

import mia.base.dao.IBaseDao;
import mia.infrastructure.vo.StrategyDetail;

import java.util.List;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */

public interface IStrategyDetailDao extends IBaseDao<StrategyDetail> {

    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyDetailReq 策略ID、奖品ID
     * @return                  返回结果
     */
    int deductStock(StrategyDetail strategyDetailReq);

}
