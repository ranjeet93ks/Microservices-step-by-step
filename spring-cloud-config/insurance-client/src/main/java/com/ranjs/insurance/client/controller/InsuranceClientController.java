package com.ranjs.insurance.client.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RefreshScope 
//to get updated info if anything got change in client side and endpoint value got chnaged for a particular url(insurance.provider.url)
public class InsuranceClientController {

	@Autowired
	@Lazy // to avoid circular dependancy issue raised cuz of @RefreshScope
	private RestTemplate template;

	@Value("${insurance.provider.url}")
	private String url;

	@GetMapping("/getPlan")
	public List<String> getPlans() {
		List<String> plans = template.getForObject(url, List.class);
		return plans;
	}

}