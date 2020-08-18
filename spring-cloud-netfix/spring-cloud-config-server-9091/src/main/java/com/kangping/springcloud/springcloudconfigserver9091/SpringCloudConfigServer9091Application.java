package com.kangping.springcloud.springcloudconfigserver9091;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServer9091Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServer9091Application.class, args);
	}

}
