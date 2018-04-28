package com.qcq.spring_boot_demo.config;

import com.qcq.spring_boot_demo.bean.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class MyAppConfig {
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
}
