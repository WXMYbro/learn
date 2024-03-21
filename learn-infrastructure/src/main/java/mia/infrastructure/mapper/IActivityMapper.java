package mia.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mia.infrastructure.vo.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IActivityMapper extends BaseMapper<Activity> {

    Activity queryActivityById(Long activityId);
}
