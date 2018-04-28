package com.qcq.spring_boot_demo.controller;

import com.qcq.spring_boot_demo.mapper.DepartmentMapper;
import com.qcq.spring_boot_demo.mapper.EmployeeMapper;
import com.qcq.spring_boot_demo.entity.Department;
import com.qcq.spring_boot_demo.entity.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	DepartmentMapper departmentMapper;
	//跳转到登录表单页面
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String login() {
		return "emp/login";
	}

	@RequestMapping("/index2")
	public String list() {
		return "emp/index";
	}
	@GetMapping("/emps")
	public String list(Model model) {
		Collection<Employee> employees= employeeMapper.getAll();
		model.addAttribute("employees", employees);
		return "emp/list";
	}
	//这个请求的处理是视频里面的示例请求
	@PostMapping("/loginByPost2")
	public String submitLogin2(@RequestParam("username") String username, @RequestParam("password") String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject currentUser=SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("principal: "+currentUser.getPrincipal().toString());
		return "emp/dashboard";
	}

	//来到员工添加页面
	@GetMapping("/emp")
	public String toAddPage(Model model) {
		Collection<Department> departments = departmentMapper.getDepartments();
		model.addAttribute("departments", departments);
		return "emp/add";
	}

	//添加员工
	@PostMapping("/emp")
	//如果前端提交出现错误：Validation failed for object='employee'
	//原因是提交的生日格式不对 默认的格式是 yyyy/MM/dd
	//可以在配置文件里面修改默认的日期格式
	//要添加BindingResult才可以忽略错误，但是生日为空
	public String addEmp(Employee employee) {
		System.out.println("新添加的员工信息: "+employee);
		employeeMapper.save(employee);
		//redirect重定向
		//forward转发
		return "redirect:/emps";
	}

	//修改员工
	@PutMapping("/emp/{id}")
	public String updateEmp(@PathVariable("id") int id,Model model) {
		Employee employee = employeeMapper.get(id);
		model.addAttribute("emp", employee);
		return "emp/add";
	}
}
