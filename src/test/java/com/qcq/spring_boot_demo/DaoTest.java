package com.qcq.spring_boot_demo;

import com.qcq.spring_boot_demo.service.PermissionService;
import com.qcq.spring_boot_demo.service.RoleService;
import com.qcq.spring_boot_demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;
	@Test
	public void testDao() {
		System.out.println(userService.selectById(1l));
		System.out.println(permissionService.selectPermissionsByUserID(1l));
		System.out.println(roleService.selectRolesByUserId(1l));
	}
}
