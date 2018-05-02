package com.qcq.spring_boot_demo.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Cat {
	@NotEmpty(message = "名字不能为空")
	private String name;
	@NotNull(message = "年龄不能为空")
	@Max(value = 10,message = "年龄最大不能高于10")
	@Min(value = 0,message = "年龄最小不能低于0")
	private int age;

	public Cat() {

	}
	@Override
	public String toString() {
		return "Cat{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public Cat(@NotEmpty(message = "名字不能为空") String name, @NotNull(message = "年龄不能为空") @Max(value = 10, message = "年龄最大不能高于10") @Min(value = 0, message = "年龄最小不能低于0") int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
