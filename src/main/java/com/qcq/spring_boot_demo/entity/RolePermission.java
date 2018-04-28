package com.qcq.spring_boot_demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author bamboo  <a href=
 * "mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题："
 * Bamboo</a>   
 * @version V1.0   
 * @Title: RolePermission.java 
 * @Package com.xm.shiro.admin.entity
 * @Description: TODO(用一句话描述该文件做什么) 
 * @date 2017-5-10 0:12:46
 */
@TableName("role_permission")
public class RolePermission implements Serializable {

	private Long rid;//角色ID
	private Long pid;//权限ID


	/**
	 * getting setting auto  generate
	 */
	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getRid() {
		return rid;
	}


	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getPid() {
		return pid;
	}


	//generate toString method
	@Override
	public String toString() {
		return "RolePermission[rid=" + rid
				+ ",pid=" + pid + "]";
	}


}
