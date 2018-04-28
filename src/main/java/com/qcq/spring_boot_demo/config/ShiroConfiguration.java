package com.qcq.spring_boot_demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	//处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
	//指定加密方式方式，也可以在这里加入缓存，当用户超过五次登陆错误就锁定该用户禁止不断尝试登陆
	//    @Bean(name = "hashedCredentialsMatcher")
	//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
	//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
	//        credentialsMatcher.setHashAlgorithmName("MD5");
	//        credentialsMatcher.setHashIterations(2);
	//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
	//        return credentialsMatcher;
	//    }
	@Bean(name = "shiroRealm")
	@DependsOn("lifecycleBeanPostProcessor")
	public ShiroRealm shiroRealm() {
		ShiroRealm realm = new ShiroRealm();
		//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	@Bean(name = "ehCacheManager")
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		return ehCacheManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setCacheManager(ehCacheManager());//用户授权/认证信息Cache, 采用EhCache 缓存
		return securityManager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

//        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();

//        LogoutFilter logoutFilter = new LogoutFilter();

//        logoutFilter.setRedirectUrl("/login");

//        filters.put("logout", logoutFilter);

//        shiroFilterFactoryBean.setFilters(filters);

		Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();

		//为了适应视频教程，这一行暂时添加，全部匿名
//		filterChainDefinitionManager.put("/**", "anon");

		filterChainDefinitionManager.put("/index", "authc");
		filterChainDefinitionManager.put("/user", "authc");
		filterChainDefinitionManager.put("/admin/**", "authc,roles[admin]");
		filterChainDefinitionManager.put("/logout", "logout");

		//这个定义会造成出乎意料的bug，jquery验证之后无法跳转页面,建议先注释一下...
//		filterChainDefinitionManager.put("/**", "authc");//其他资源全部拦截

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthz");

		return shiroFilterFactoryBean;
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}

//	thymeleaf模板引擎和shiro整合时使用
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}

