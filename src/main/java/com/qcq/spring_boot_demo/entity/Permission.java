package com.qcq.spring_boot_demo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * @author bamboo  <a href=
 * "mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题："
 * Bamboo</a>   
 * @version V1.0   
 * @Title: Permission.java 
 * @Package com.xm.shiro.admin.entity
 * @Description: TODO(用一句话描述该文件做什么) 
 * @date 2017-5-10 0:00:45
 */
@TableName("permission")
public class Permission implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
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
