package com.step.template.main.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.step.template.main.config.annotation.Auth;
import com.step.template.main.entity.Device;
import com.step.template.main.service.DeviceService;
import com.step.template.main.svo.DeviceSvo;
import com.step.template.main.vo.DeviceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "增删改查测试")
@AllArgsConstructor
@RestController
@RequestMapping("devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Auth("device:query")
    @ApiOperation("获取列表")
    @GetMapping
    public Page<DeviceVo> selectWithPage(DeviceSvo svo) {
        return deviceService.selectWithPage(svo);
    }

    @Auth("device:query")
    @ApiOperation("获取单个数据")
    @GetMapping("{id}")
    public DeviceVo selectById(@PathVariable Integer id) {
        return deviceService.getVoById(id);
    }

    @Auth("device:edit")
    @ApiOperation("创建")
    @PostMapping
    public void insert(@RequestBody Device device) {
        deviceService.save(device);
    }

    @Auth("device:edit")
    @ApiOperation("编辑")
    @PutMapping
    public void updateById(@RequestBody Device device) {
        deviceService.updateById(device);
    }

    @Auth("device:edit")
    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        deviceService.removeById(id);
    }
}
