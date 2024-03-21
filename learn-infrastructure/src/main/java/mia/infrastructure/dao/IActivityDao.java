package mia.infrastructure.dao;


import mia.base.dao.IBaseDao;
import mia.infrastructure.vo.Activity;


/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */

public interface IActivityDao extends IBaseDao<Activity> {

   void insert(Activity req);

   Activity queryActivityById(Long activityId);

}
