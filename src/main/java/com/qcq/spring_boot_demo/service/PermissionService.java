package com.qcq.spring_boot_demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.qcq.spring_boot_demo.entity.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
	public List<Permission> selectPermissionsByUserID(Long id);
}
