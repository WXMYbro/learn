package mia.base.dao;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;


@Slf4j
public class BaseDaoImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseDao<T> {

    @Override
    public void updateProtect(T entity, List<SFunction<T, ?>> fields) {
        boolean dataProtect = false;
        // 1.通过反射获取id的值
        Field id = ReflectUtil.getField(entity.getClass(), "id");
        Long idValue = (Long) ReflectUtil.getFieldValue(entity, id);
        // 2.通过反射获取pkf的值
        T t = baseMapper.selectById(idValue);
        Field pkf = ReflectUtil.getField(t.getClass(), "pkf");
        Integer pkfValue = (Integer) ReflectUtil.getFieldValue(t, pkf);
        if (ObjectUtil.isNull(pkfValue)) {
            return;
        }
    }

    @Override
    public void removeProtect(Serializable id) {
        Integer pkfValue;
        try {
            T t = baseMapper.selectById(id);
            Field pkf = ReflectUtil.getField(t.getClass(), "pkf");
            pkfValue = (Integer) ReflectUtil.getFieldValue(t, pkf);
        } catch (Exception e) {
            log.error("删除数据检查数据保护错误：{}", e.getMessage());

        }
    }

    @Override
    public boolean isFieldExist(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(field, value);
        return baseMapper.selectCount(wrapper) > 0;
    }

    @Override
    public Long countFieldSize(SFunction<T, ?> field, Object value) {
        return null;
    }

    @Override
    public T getObjectByFiled(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(field, value);
        List<T> list = baseMapper.selectList(wrapper);
        if (list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            log.warn(">>>>>>BaseDao-getObjectByFiled>>>>>>：值为{}的对象大于1条", value);
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<T> getListByFiled(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(field, value);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public T getOne(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(field, value);
        return getOne(wrapper);
    }

    @Override
    public T getOne(LambdaQueryWrapper<T> wrapper) {
        List<T> list = baseMapper.selectList(wrapper);
        if (list.size() == 1) {
            return list.get(0);
        }
        if (list.size() > 1) {
            log.error("getOne错误，出现多条记录！");
            return list.get(0);
        }
        return null;
    }
}