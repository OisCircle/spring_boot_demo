package com.qcq.spring_boot_demo.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qcq.spring_boot_demo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
