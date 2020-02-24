package com.meep.configuration;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class TechtestConfigurationTest {
	
	@Test
	public void shouldReturnBeans() {
		TechtestConfiguration techtestConfiguration = new TechtestConfiguration();
		assertThat(techtestConfiguration.resources()).isExactlyInstanceOf(ArrayList.class);
		assertThat(techtestConfiguration.restTemplate()).isExactlyInstanceOf(RestTemplate.class);
	}
}
