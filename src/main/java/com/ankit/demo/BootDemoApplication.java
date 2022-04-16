package com.ankit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class BootDemoApplication {
//extends SpringBootServletInitializer for external tomcat
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BootDemoApplication.class, args);
		//Student student = context.getBean(Student.class);
		//student.printStudent();
	}
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(BootDemoApplication.class); }
	 */

}
