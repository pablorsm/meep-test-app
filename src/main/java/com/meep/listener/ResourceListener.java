package com.meep.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.meep.dto.ResourceDTO;
import com.meep.service.ResourceService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ResourceListener {
	
	private ResourceService resourceService;
	
	List<ResourceDTO> modifiedResources;
	
	@Autowired
	public ResourceListener(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	//Runs every 60 seconds with an initial delay of 1 sec, waits until the previous execution is done (no async call or parallel)
	@Scheduled(fixedRate = 60000, initialDelay = 1000)
	public void detectChanges() {
		this.modifiedResources = resourceService.getUpdatedResources();
		log.info("Updated resources:\n{}", StringUtils.collectionToDelimitedString(modifiedResources,"\n"));
	}
	
	public List<ResourceDTO> getModifiedResources(){
		return this.modifiedResources;
	}
}
