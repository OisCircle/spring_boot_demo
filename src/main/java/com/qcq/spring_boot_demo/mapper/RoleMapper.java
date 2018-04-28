package com.qcq.spring_boot_demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qcq.spring_boot_demo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
	List<Role> selectRolesByUserId(Long id);
}
