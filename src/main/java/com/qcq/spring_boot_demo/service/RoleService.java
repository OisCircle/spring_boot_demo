package com.qcq.spring_boot_demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.qcq.spring_boot_demo.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
	public List<Role> selectRolesByUserId(Long id);
}
