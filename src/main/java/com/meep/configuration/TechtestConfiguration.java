package com.meep.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.meep.domain.Resource;

@Configuration
public class TechtestConfiguration {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	    
	}
	
	@Bean
	public List<Resource> resources(){
		return new ArrayList<>();
	}
}
