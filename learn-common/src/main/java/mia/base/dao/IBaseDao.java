package mia.base.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;


/**
 * 扩展mybatis-plus的baseMapper，主要增加如下查询方法：
 * 1.判断字段是否存在
 * 2.重写获取一条记录方法
 * 3.判断字段存在的数量
 * 4.根据字段获取列表
 *
 * @author lqa
 * @since 2022/6/6 09:25
 */
public interface IBaseDao<T> extends IService<T> {

    /**
     * 更新数据检查数据保护
     *
     * @param entity 修改的对象
     * @param fields 希望不被修改的字段
     */
    void updateProtect(T entity, List<SFunction<T, ?>> fields);

    /**
     * 删除数据检查数据保护
     *
     * @param id 主键
     */
    void removeProtect(Serializable id);


    /**
     * 判断字段值是否存在
     *
     * @param field 字段
     * @param value 值
     * @return boolean
     * @author lqa
     * @since 2022/6/6 09:25
     */
    boolean isFieldExist(SFunction<T, ?> field, Object value);

    /**
     * 判断字段存在的数量
     *
     * @param field 字段
     * @param value 值
     * @return java.lang.Long
     * @author lqa
     * @since 2022/6/6 09:25
     */
    Long countFieldSize(SFunction<T, ?> field, Object value);

    /**
     * 根据字段获取对象
     *
     * @param field 字段
     * @param value 值
     * @return T
     * @author lqa
     * @since 2022/8/5 15:04
     */
    T getObjectByFiled(SFunction<T, ?> field, Object value);

    /**
     * 根据字段获取列表
     *
     * @param field 字段
     * @param value 值
     * @return java.util.List<T>
     * @author lqa
     * @since 2022/8/5 15:05
     */
    List<T> getListByFiled(SFunction<T, ?> field, Object value);

    /**
     * 重写获取一条记录方法
     * 如果出现多条记录，仅控制台打印错误，不抛出错误，
     * 且只取第一条数据
     *
     * @param field 字段
     * @param value 值
     * @return T
     * @author lqa
     * @since 2022/8/5 15:05
     */
    T getOne(SFunction<T, ?> field, Object value);

    /**
     * 重写获取一条记录方法
     * 如果出现多条记录，仅控制台打印错误，不抛出错误，
     * 且只取第一条数据
     *
     * @param lambdaQueryWrapper lambdaQueryWrapper对象
     * @return T
     * @author lqa
     * @since 2022/8/5 15:06
     */
    T getOne(LambdaQueryWrapper<T> lambdaQueryWrapper);


}
