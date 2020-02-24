package com.meep.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meep.adapter.ResourceAdapter;
import com.meep.domain.Resource;
import com.meep.dto.ResourceDTO;
import com.meep.provider.ResourceProvider;
import com.meep.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	ResourceProvider resourceProvider;
	List<Resource> resources;
	ResourceAdapter resourceAdapter;

	@Autowired
	public ResourceServiceImpl(ResourceProvider resourceProvider, List<Resource> resources,
			ResourceAdapter resourceAdapter) {
		this.resourceProvider = resourceProvider;
		this.resources = resources;
		this.resourceAdapter = resourceAdapter;
	}

	@Override
	public List<ResourceDTO> getUpdatedResources() {
		List<Resource> newResources = resourceProvider.getResources();
		List<ResourceDTO> returnedResources = resources.stream().filter(r -> !newResources.contains(r))
				.map(p -> resourceAdapter.toDto(p, false)).collect(Collectors.toList());

		// Add new resources, no lambda expression used to avoid mutating problems
		for (Resource newResource : newResources) {
			if (!resources.contains(newResource))
				returnedResources.add(resourceAdapter.toDto(newResource, true));
		}
		// Update context with the new data
		resources = newResources;
		return returnedResources;
	}

}
