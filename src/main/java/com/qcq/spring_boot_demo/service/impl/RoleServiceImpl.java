package com.qcq.spring_boot_demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qcq.spring_boot_demo.entity.Role;
import com.qcq.spring_boot_demo.mapper.RoleMapper;
import com.qcq.spring_boot_demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
	@Autowired
	RoleMapper roleMapper;
	@Override
	public List<Role> selectRolesByUserId(Long id) {
		return roleMapper.selectRolesByUserId(id);
	}
}
