package com.fcgl.madrid;

import com.fcgl.madrid.forum.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MadridApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadridApplication.class, args);
	}

}
