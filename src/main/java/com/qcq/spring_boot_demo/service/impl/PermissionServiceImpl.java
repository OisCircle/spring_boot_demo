package com.qcq.spring_boot_demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qcq.spring_boot_demo.entity.Permission;
import com.qcq.spring_boot_demo.mapper.PermissionMapper;
import com.qcq.spring_boot_demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
	@Autowired
	PermissionMapper permissionMapper;
	@Override
	public List<Permission> selectPermissionsByUserID(Long id) {
		return permissionMapper.selectPermissionsByUserID(id);
	}
}
