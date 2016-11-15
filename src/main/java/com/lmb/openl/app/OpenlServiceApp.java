package com.lmb.openl.app;

import com.lmb.openl.controller.HelloRuleController;
import com.lmb.openl.domain.AbstractRule;
import com.lmb.openl.exception.RuleServiceException;
import com.lmb.openl.service.HelloRuleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
@ComponentScan(basePackageClasses = {HelloRuleController.class, HelloRuleService.class, RuleServiceException.class,AbstractRule.class})
@PropertySource( value={"classpath:application.properties"}, ignoreResourceNotFound = false)

public class OpenlServiceApp {

	public static final String RulePathFileName = "/Users/n0292928/EIS/LMB_Workspace/SimpleOpenl/src/main/openl/rules/TemplateRules_Multiple.xls";
	public static void main(String[] args) {

		SpringApplication.run(OpenlServiceApp.class, args);
	}

	@Value("${lmb.openl.rule.pathFileName}")
	public String       rulesPathFileName;

	public String getRulesPathFileName() {  return rulesPathFileName;  }

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
