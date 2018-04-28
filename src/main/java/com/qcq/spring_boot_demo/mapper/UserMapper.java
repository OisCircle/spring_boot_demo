package com.qcq.spring_boot_demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qcq.spring_boot_demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}
