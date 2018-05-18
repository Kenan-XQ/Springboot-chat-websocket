package com.xq.websocketchatdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebsocketChatDemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WebsocketChatDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WebsocketChatDemoApplication.class);
	}
}
