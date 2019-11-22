package com.step.template.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.step.template.main.entity.Device;
import com.step.template.main.svo.DeviceSvo;
import com.step.template.main.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceMapper extends BaseMapper<Device> {
    Page<DeviceVo> selectWithPage(Page<Device> page, @Param("svo") DeviceSvo svo);
}
