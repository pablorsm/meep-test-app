package com.meep.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.meep.adapter.ResourceAdapter;
import com.meep.domain.Resource;
import com.meep.dto.ResourceDTO;
import com.meep.provider.ResourceProvider;
import com.meep.test.utils.TestUtils;

public class ResourceServiceImplTest {
	
	TestUtils testUtils = new TestUtils();
	
	ResourceProvider mockResourceProvider;
	
	@BeforeEach
	public void init() {
		mockResourceProvider = mock(ResourceProvider.class);
	}
	
	@Test
	public void shouldGetNewAvailableUpdatedResources() {
		ResourceAdapter adapter =  new ResourceAdapter();
		Resource firstUpdatedResource = testUtils.createResource();
		Resource secondUpdatedResource = testUtils.createResource();
		when(mockResourceProvider.getResources()).thenReturn(Arrays.asList(firstUpdatedResource, secondUpdatedResource));
		ResourceServiceImpl resourceService = new ResourceServiceImpl(mockResourceProvider, Arrays.asList(testUtils.createResource()), adapter);
		List<ResourceDTO> resourcesUpdated = resourceService.getUpdatedResources();
		//Initial element which is no longer available and the new 2
		assertThat(resourcesUpdated.size()).isEqualTo(3);
		//Assert that two new resources are available
		assertThat(resourcesUpdated).contains(adapter.toDto(firstUpdatedResource, true));
		assertThat(resourcesUpdated).contains(adapter.toDto(secondUpdatedResource, true));
	}
	
	@Test
	public void shouldGetNotAvailableUpdatedResources() {
		ResourceAdapter adapter =  new ResourceAdapter();
		Resource firstUpdatedResource = testUtils.createResource();
		Resource secondUpdatedResource = testUtils.createResource();
		when(mockResourceProvider.getResources()).thenReturn(Arrays.asList(testUtils.createResource()));
		ResourceServiceImpl resourceService = new ResourceServiceImpl(mockResourceProvider, Arrays.asList(firstUpdatedResource, secondUpdatedResource), adapter);
		List<ResourceDTO> resourcesUpdated = resourceService.getUpdatedResources();
		//Initial elements which are no longer available and the new one
		assertThat(resourcesUpdated.size()).isEqualTo(3);
		//Assert that two new resources are no longer available
		assertThat(resourcesUpdated).contains(adapter.toDto(firstUpdatedResource, false));
		assertThat(resourcesUpdated).contains(adapter.toDto(secondUpdatedResource, false));
	}

	@Test
	public void shouldGetAnEmptyListWNoUpdatedResources() {
		ResourceAdapter adapter =  new ResourceAdapter();
		Resource firstUpdatedResource = testUtils.createResource();
		Resource secondUpdatedResource = testUtils.createResource();
		when(mockResourceProvider.getResources()).thenReturn(Arrays.asList(firstUpdatedResource, secondUpdatedResource));
		ResourceServiceImpl resourceService = new ResourceServiceImpl(mockResourceProvider, Arrays.asList(firstUpdatedResource, secondUpdatedResource), adapter);
		List<ResourceDTO> resourcesUpdated = resourceService.getUpdatedResources();
		//The list returned by provider is the same so no changes are present
		assertThat(resourcesUpdated).isEmpty();
	}

}
