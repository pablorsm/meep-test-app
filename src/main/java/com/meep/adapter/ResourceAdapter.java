package com.meep.adapter;

import org.springframework.stereotype.Component;

import com.meep.domain.Resource;
import com.meep.dto.ResourceDTO;

@Component
public class ResourceAdapter {
	public ResourceDTO toDto(Resource resource, Boolean isAvailable) {
		return ResourceDTO.builder().available(isAvailable)
				.companyZoneId(resource.getCompanyZoneId())
				.id(resource.getId())
				.name(resource.getName())
				.x(resource.getX())
				.y(resource.getY())
				.batteryLevel(resource.getBatteryLevel())
				.engineType(resource.getEngineType())
				.helmets(resource.getHelmets())
				.licencePlate(resource.getLicencePlate())
				.model(resource.getModel())
				.pricePerMinuteDriving(resource.getPricePerMinuteDriving())
				.pricePerMinuteParking(resource.getPricePerMinuteParking())
				.range(resource.getRange())
				.realTimeData(resource.getRealTimeData())
				.resourceImageId(resource.getResourceImageId())
				.resourceType(resource.getResourceType())
				.seats(resource.getSeats())
				.build();
	}
}
