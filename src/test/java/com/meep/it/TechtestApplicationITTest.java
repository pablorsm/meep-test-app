package com.meep.it;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.meep.TechtestApplication;
import com.meep.domain.Resource;
import com.meep.dto.ResourceDTO;
import com.meep.listener.ResourceListener;
import com.meep.test.utils.TestUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TechtestApplicationITTest {

	TestUtils testUtils = new TestUtils();

	@Autowired
	ResourceListener resourceListener;

	@MockBean
	RestTemplate mockRestTemplate;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void init() throws IOException {
		ResponseEntity<List<Resource>> responseEntityForResource = new ResponseEntity<List<Resource>>(
				testUtils.getJsonFileAsList("resources.json"), HttpStatus.OK);
		when(mockRestTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class),
				any(ParameterizedTypeReference.class))).thenReturn(responseEntityForResource);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldUpdateTheResources() throws IOException, InterruptedException {
		Thread.sleep(1000);// NOSONAR
		List<ResourceDTO> oldResources = resourceListener.getModifiedResources();
		ResponseEntity<List<Resource>> responseEntityForResource = new ResponseEntity<List<Resource>>(
				testUtils.getJsonFileAsList("resources_new_call.json"), HttpStatus.OK);
		when(mockRestTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class),
				any(ParameterizedTypeReference.class))).thenReturn(responseEntityForResource);
		await().atMost(70, TimeUnit.SECONDS).untilAsserted(() -> {
			try {
				assertThat(resourceListener.getModifiedResources()).isNotEqualTo(oldResources);
				log.info("Old bean: {}\nNew bean: {}", oldResources.toString(),
						resourceListener.getModifiedResources().toString());
				return;
			} catch (AssertionError e) {
				log.debug("Assertion error: {}", e.getMessage());
				assert false;
			}
			assert false;
		});
	}

	@Test
	public void shouldNotUpdateTheResources() throws InterruptedException, IOException {
		Thread.sleep(1000);// NOSONAR
		await().atMost(70, TimeUnit.SECONDS).untilAsserted(() -> {
			try {
				assertThat(resourceListener.getModifiedResources()).isEmpty();
				log.info("Changed resources: {}", resourceListener.getModifiedResources());
				return;
			} catch (AssertionError e) {
				log.debug("Assertion error: {}", e.getMessage());
				assert false;
			}
			assert false;
		});
	}

	@Test
	public void main() {
		TechtestApplication.main(new String[] {});
		assertThat(true).isTrue();
	}

}
