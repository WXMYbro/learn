package mia.common;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.hutool.core.bean.BeanUtil.getPropertyDescriptor;
import static cn.hutool.core.bean.BeanUtil.getPropertyDescriptors;

/**
 * @author yanliuyang
 * @date 2023年04月18日 13:22
 */
public class BaseConvertUtil {

    public static <R ,Z>Page<R> convertToVoPage(IPage<Z> sourcePage,  Class<R> targetClazz) {
        //判空
        if(sourcePage==null||targetClazz==null){
            throw new NullPointerException("类型转换出错,请检查传入参数");
        }
        Page<R> resultPage = new Page<>(sourcePage.getCurrent(), sourcePage.getSize(), sourcePage.getTotal());
        resultPage.setRecords(listConver(sourcePage.getRecords(),targetClazz));
        return resultPage;
    }

    public static <T> T objectConver(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T vo = null;
        try {
            vo = clazz.newInstance();
            BeanUtils.copyProperties(source, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }
    public static <T> List<T> listConver(List<?> source, Class<T> clazz) {
        if (source == null || source.size() == 0) {
            return new ArrayList<>();
        }
        List<T> target = new ArrayList<>();
        for (Object obj : source) {
            T t = null;
            try {
                t = clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            BeanUtils.copyProperties(obj, t);
            target.add(t);
        }
        return target;
    }
    /**
     *
     * @param source 复制源
     * @param target 目标
     * @throws BeansException BeansException
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        copyProperties(source, target, (String[]) null);
    }
    private static void copyProperties(Object source, Object target, String... ignoreProperties)
            throws BeansException {
        org.springframework.util.Assert.notNull(source, "Source must not be null");
        Assert.<Object>notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (value != null) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }
    }
}
