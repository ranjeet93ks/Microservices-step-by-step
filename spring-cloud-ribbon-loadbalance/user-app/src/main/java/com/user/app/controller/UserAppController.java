package com.user.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.app.config.RibbonConfiguration;

@SpringBootApplication
@RestController
@RibbonClient(name = "chatbook", configuration = RibbonConfiguration.class)
public class UserAppController {

	@Autowired
	private RestTemplate template;

	@GetMapping("/invoke")
	public String invokeCharbook() {
		return template.getForObject("http://chatbook/chatbook-application/chat", String.class);
	}

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}
}
