package com.qcq.spring_boot_demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qcq.spring_boot_demo.entity.Bird;
import org.springframework.stereotype.Component;

/**
 * @Author O
 * @Description
 * @Date 2018/4/25 14:18
 * @Version 1.0
 */
@Component
public interface BirdMapper extends BaseMapper<Bird> {

}
