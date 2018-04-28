package com.qcq.spring_boot_demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShiroTest {
	Logger logger = LoggerFactory.getLogger(ShiroTest.class);

	@Test
	public void testLogin() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);

		Subject currentUser = SecurityUtils.getSubject();
		System.out.println(currentUser == null);

		Session session = currentUser.getSession();
		session.setAttribute("key", "value");

		String value = (String) session.getAttribute("key");
		System.out.println(value);

		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("wangwu", "123");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
				//if no exception, that's it, we're done!
			} catch (UnknownAccountException uae) {
				System.out.println("no such user");
				//username wasn't in the system, show them an error message?
			} catch (IncorrectCredentialsException ice) {
				System.out.println("password wrong");
				//password didn't match, try again?
			} catch (LockedAccountException lae) {
				System.out.println("account is locked");
				//account for that username is locked - can't login.  Show them a message?
			} catch (AuthenticationException ae) {
				System.out.println("unexpected error");
				//unexpected condition - error?
			}
		}

		System.out.println(currentUser.getPrincipal() + " has login!");
		System.out.println(currentUser.hasRole("witcher"));
		System.out.println(currentUser.isPermitted("lightsaber:weild"));

		currentUser.logout();
		System.out.println(currentUser == null);
		System.out.println(currentUser.getPrincipal());
		System.out.println(currentUser.getSession().getAttribute("key"));
	}

	@Test
	public void testRealm() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

	}

	@Test
	public void testSpringConfig() {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("wangwu", "123");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(currentUser.getPrincipal() + " has login!");
		}


		System.out.println(currentUser.isAuthenticated());
		currentUser.logout();
		System.out.println(currentUser.isAuthenticated());


	}

	@Test
	public void md5Test() {
		char[] md5Password = new Md5Hash("a", "a", 998).toString().toCharArray();
		System.out.println(md5Password);
	}
}
