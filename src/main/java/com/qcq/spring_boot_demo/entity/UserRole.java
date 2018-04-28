package com.qcq.spring_boot_demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author bamboo  <a href=
 * "mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题："
 * Bamboo</a>   
 * @version V1.0   
 * @Title: UserRole.java 
 * @Package com.xm.shiro.admin.entity
 * @Description: TODO(用一句话描述该文件做什么) 
 * @date 2017-5-10 0:13:30
 */
@TableName("user_role")
public class UserRole implements Serializable {

	private Long uid;//用户ID
	private Long rid;//角色ID


	/**
	 * getting setting auto  generate
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getUid() {
		return uid;
	}


	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getRid() {
		return rid;
	}


	//generate toString method
	@Override
	public String toString() {
		return "UserRole[uid=" + uid
				+ ",rid=" + rid + "]";
	}


}
