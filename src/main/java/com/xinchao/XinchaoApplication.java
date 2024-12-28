package com.xinchao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ServletComponentScan
@EnableJpaRepositories(basePackages = "com.xinchao.repository")

public class XinchaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XinchaoApplication.class, args);
	}

}
