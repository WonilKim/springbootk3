package com.rubypaper;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.rubypaper", "com.ruby"})
public class Chapter01Application {

	public static void main(String[] args) {
		SpringApplication application = 
new SpringApplication(Chapter01Application.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}


}
