package com.meep.provider.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.meep.domain.Resource;
import com.meep.provider.ConfigProvider;
import com.meep.test.utils.TestUtils;

public class ResourceProviderImplTest {
	
	TestUtils testUtils = new TestUtils();
	
	RestTemplate mockRestTemplate;
	ConfigProvider mockConfigProvider;
	
	@BeforeEach
	public void init() {
		mockConfigProvider = mock(ConfigProvider.class);
		when(mockConfigProvider.getMeepApidevUrlFixed())
				.thenReturn("http://meepApiDevUrlFixed.com");
		mockRestTemplate = mock(RestTemplate.class);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetResources() {
		ResponseEntity<List<Resource>> responseEntityForResource = new ResponseEntity<List<Resource>>(
				Arrays.asList(testUtils.createResource()),
				HttpStatus.OK);
		when(mockRestTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class),
                any(ParameterizedTypeReference.class))).thenReturn(responseEntityForResource);
		ResourceProviderImpl resourceProvider = new ResourceProviderImpl(mockConfigProvider, mockRestTemplate);
		List<Resource> resources = resourceProvider.getResources();
		assertThat(resources).hasAtLeastOneElementOfType(Resource.class);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldFailGettingResources() {
		when(mockRestTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class),
                any(ParameterizedTypeReference.class))).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		ResourceProviderImpl resourceProvider = new ResourceProviderImpl(mockConfigProvider, mockRestTemplate);
		List<Resource> resources = resourceProvider.getResources();
		assertThat(resources).isEmpty();
	}
}
