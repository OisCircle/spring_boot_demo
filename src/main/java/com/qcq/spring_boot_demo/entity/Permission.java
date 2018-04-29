package com.qcq.spring_boot_demo.entity;

import java.io.Serializable;

public class Permission implements Serializable {
	private Long id;//
	private String url;//url地址
	private String description;//描述


	/**
	 * getting setting auto  generate
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


	@Override
	public String toString() {
		return "Permission{" +
				"id=" + id +
				", url='" + url + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
