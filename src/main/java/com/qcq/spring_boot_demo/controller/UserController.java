package com.qcq.spring_boot_demo.controller;


import com.qcq.spring_boot_demo.bean.Cat;
import com.qcq.spring_boot_demo.bean.Dog;
import com.qcq.spring_boot_demo.mapper.RoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 *   
 *
 * @author bamboo  <a href="mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题：">Bamboo</a>   
 * @version V1.0   
 * @Title: CityRestController.java 
 * @Package com.bamboo.springboot.controller 
 * @Description: 用户登陆权限认证管理控制器
 * @date 2017年4月21日 下午5:51:36 
 */
@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RoleMapper roleMapper;

	//跳转到登录表单页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		Cat cat=new Cat();
		cat.setAge(1);
		cat.setName("catty");
		model.addAttribute("a", cat);
		return "login";
	}
	@RequestMapping(value = "/unauthz")
	@ResponseBody
	public String unauthz() {
		return "unauthz";
	}

	@RequestMapping("/index")
	public String list() {
		return "index";
	}


	/**
	 * ajax登录请求接口方式登陆
	 *
	 */
	//相当于
//	@PostMapping(value = "/loginByPost")
	//这个请求的处理是我之前自己写的简单登陆页面的url处理
	@RequestMapping(value = "/loginByPost", method = RequestMethod.POST)
	@ResponseBody
	public int submitLogin(@RequestParam("username") String username,
	                       @RequestParam("password") String password,
	                       HttpSession session,
	                       Model model) {
		int status=0;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject currentUser=SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			//登陆成功设置为1，session添加用户
			session.setAttribute("user", currentUser.getPrincipal());
			model.addAttribute("user", currentUser.getPrincipal());
			status=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("principal: "+currentUser.getPrincipal().toString());
		return status;
	}

	//Restful CURD
	Dog dog=new Dog();

	@GetMapping("/user")
	@ResponseBody
	public String getUser(Dog dog) {
		System.out.println(dog);
		System.out.println("get");
		return "success";
	}

	@PutMapping("/user")
	@ResponseBody
	public String updateUser(Dog dog) {
		System.out.println("put");
		System.out.println(dog);
		return "success";
	}

	@PostMapping("/user")
	public String addUser() {
		System.out.println("post");
		return "success";
	}

	@DeleteMapping("/user")
	public String deleteUser() {
		System.out.println("delete");
		return "success";
	}
}

