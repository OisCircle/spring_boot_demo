package com.qcq.spring_boot_demo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

@TableName("role")
public class Role implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;//
	private String name;//角色名称
	private String description;//角色类型


	/**
	 * getting setting auto  generate
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


	//generate toString method
	@Override
	public String toString() {
		return "Role[id=" + id
				+ ",name=" + name
				+ ",description=" + description + "]";
	}


}
