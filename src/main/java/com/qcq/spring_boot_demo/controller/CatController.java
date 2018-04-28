package com.qcq.spring_boot_demo.controller;

import com.qcq.spring_boot_demo.bean.Cat;
import com.qcq.spring_boot_demo.responseStandar.ResponseCode;
import com.qcq.spring_boot_demo.responseStandar.ResponseMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author O
 * @Description 数据校验测试
 * @Date 2018/4/24 20:34
 * @Version 1.0
 */
@Controller
public class CatController {
	@PostMapping("/cat")
	@ResponseBody
	public ResponseMessage cat(@Validated Cat cat, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessage=bindingResult.getFieldError().getDefaultMessage();
			System.out.println(errorMessage);
			return new ResponseMessage(ResponseCode.FAILUREA, errorMessage);
		}
		System.out.println(cat);
		return new ResponseMessage(ResponseCode.SUCCESS, cat);
	}
}
