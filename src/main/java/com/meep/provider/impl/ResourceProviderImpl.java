package com.meep.provider.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.meep.domain.Resource;
import com.meep.provider.ConfigProvider;
import com.meep.provider.ResourceProvider;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ResourceProviderImpl implements ResourceProvider {

	ConfigProvider configProvider;
	RestTemplate restTemplate;

	@Autowired
	public ResourceProviderImpl(ConfigProvider configProvider, RestTemplate restTemplate) {
		this.configProvider = configProvider;
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Resource> getResources() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		try {
			ResponseEntity<List<Resource>> responseEntity = restTemplate.exchange(
					UriComponentsBuilder.fromHttpUrl(configProvider.getMeepApidevUrlFixed()).build().toUri(),
					HttpMethod.GET, entity, new ParameterizedTypeReference<List<Resource>>() {
					});
			return Optional.ofNullable(responseEntity.getBody()).isPresent() ? responseEntity.getBody()
					: new ArrayList<>();
		} catch (HttpClientErrorException ex) {
			log.error("Error retrieving data from the endpoint with status {}:{}", ex.getStatusCode(), ex.getStatusText());
		}
		return new ArrayList<>();
	}

}
