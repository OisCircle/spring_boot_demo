package com.qcq.spring_boot_demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("user")
public class User implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;//
	private String username;//用户昵称
	private String password;//密码
	@TableField("create_time")
	private Date createTime;//创建时间
	@TableField("last_login_time")
	private Date lastLoginTime;//最后登录时间
	@TableField("is_forbidden")
	private Long isForbidden;//1:有效，0:禁止登录
	@TableField(exist = false)
	private List<String> roles;
	@TableField(exist = false)
	private List<String> permissions;

	/**
	 * getting setting auto  generate
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setIsForbidden(Long isForbidden) {
		this.isForbidden = isForbidden;
	}

	public Long getIsForbidden() {
		return isForbidden;
	}


	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", createTime=" + createTime +
				", lastLoginTime=" + lastLoginTime +
				", isForbidden=" + isForbidden +
				", roles=" + roles +
				", permissions=" + permissions +
				'}';
	}
}
