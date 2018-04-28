package com.qcq.spring_boot_demo.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qcq.spring_boot_demo.entity.Permission;
import com.qcq.spring_boot_demo.entity.Role;
import com.qcq.spring_boot_demo.entity.User;
import com.qcq.spring_boot_demo.service.PermissionService;
import com.qcq.spring_boot_demo.service.RoleService;
import com.qcq.spring_boot_demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取用户的角色和权限信息
 * Created by bamboo on 2017/5/10.
 */
public class ShiroRealm extends AuthorizingRealm {

	@Value("${timeOfHash}")
	int timeOfHash;
	private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 登录认证
	 *
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		//查出是否有此用户
		User user=userService.selectOne(new EntityWrapper<User>().eq("username",token.getUsername()));

		if (user != null) {
			//md5+盐值+998
			char[] md5Password = new Md5Hash(token.getPassword(), token.getUsername(), timeOfHash).toString().toCharArray();
			//token的明文密码修改为加密后的密码
			token.setPassword(md5Password);
			// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
			List<Role> rlist = roleService.selectRolesByUserId(user.getId());//获取用户角色
			List<Permission> plist = permissionService.selectPermissionsByUserID(user.getId());//获取用户权限
			List<String> roleStrlist = new ArrayList<String>();////用户的角色集合
			List<String> perminsStrlist = new ArrayList<String>();//用户的权限集合
			for (Role role : rlist) {
				roleStrlist.add(role.getName());
			}
			for (Permission permission : plist) {
				perminsStrlist.add(permission.getUrl());
			}
			user.setRoles(roleStrlist);
			user.setPermissions(perminsStrlist);
//            Session session = SecurityUtils.getSubject().getSession();
//            session.setAttribute("user", user);//成功则放入session
			// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验

			// 第一个参数user是到时候在currentUser的getPricipal方法里面拿出来的
			// 如果用user.getUsername()方法,则会报错
			//java.lang.String cannot be cast to com.qcq.spring_boot_demo.entity.User
			return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		}
		return null;
	}

	/**
	 * 权限认证
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getDescription()).iterator().next();
//        String loginName = (String) super.getAvailablePrincipal(principalCollection);
		User user = (User) principalCollection.getPrimaryPrincipal();
//        //到数据库查是否有此对象
//        User user = null;// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        user = userMapper.findByName(loginName);
		if (user != null) {
			//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			//用户的角色集合
			info.addRoles(user.getRoles());
			//用户的权限集合
			info.addStringPermissions(user.getPermissions());

			return info;
		}
		// 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
		return null;
	}


}