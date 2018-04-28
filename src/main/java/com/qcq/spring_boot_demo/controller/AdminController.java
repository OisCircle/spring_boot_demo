package com.qcq.spring_boot_demo.controller;

import com.qcq.spring_boot_demo.responseStandar.ResponseCode;
import com.qcq.spring_boot_demo.responseStandar.ResponseMessage;
import com.qcq.spring_boot_demo.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("")
	public String admin() {
		return "admin";
	}

	@PostMapping("/addUser")
	@RequiresPermissions("user:add")
	@ResponseBody
	public ResponseMessage addUser(String username) {
		List<List<User>> listUsers=new ArrayList<>();
		List<User> users = new ArrayList<>();
		User user = new User();
		User user1=new User();
		user.setUsername(username);
		user.setPassword("1231231");
		users.add(user);
		user1.setUsername("李氏");
		user1.setPassword("asd123");
		users.add(user1);
		listUsers.add(users);

		List<User> users2 = new ArrayList<>();
		User user2 = new User();
		User user3=new User();
		user2.setUsername("heheda");
		user2.setPassword("ggvbbs");
		users2.add(user2);
		user3.setUsername("ojjk");
		user3.setPassword("88888");
		users2.add(user3);
		listUsers.add(users2);
		return new ResponseMessage(ResponseCode.SUCCESS, listUsers);
	}
//	public String addUser(String username) {
//		return "username: " + username;
//	}
	@RequestMapping("/deleteUser")
	@RequiresPermissions("user:delete")
	@ResponseBody
	public String deleteUser() {
		return "success";
	}
	@RequestMapping("/updateUser")
	@RequiresPermissions("user:update")
	@ResponseBody
	public String updateUser() {
		return "success";
	}
	@RequestMapping("/selectUser")
	@RequiresPermissions("user:select")
	@ResponseBody
	public String selectUser() {
		return "success";
	}
}
