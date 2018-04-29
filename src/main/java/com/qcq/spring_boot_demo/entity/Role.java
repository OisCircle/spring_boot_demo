package com.qcq.spring_boot_demo.entity;

import java.io.Serializable;

public class Role implements Serializable {
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
