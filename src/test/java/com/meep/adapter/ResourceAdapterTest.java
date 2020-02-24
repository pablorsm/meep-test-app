package com.meep.adapter;

import org.junit.jupiter.api.Test;

import com.meep.domain.Resource;
import com.meep.dto.ResourceDTO;
import com.meep.test.utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceAdapterTest {
	
	TestUtils testUtils = new TestUtils();
	
	@Test
	public void shoudlConvertDomainObjecttoDto() {
		ResourceAdapter resourceAdapter = new ResourceAdapter();
		Resource resource = testUtils.createResource();
		ResourceDTO resourceDto = resourceAdapter.toDto(resource, true);
		assertThat(resourceDto.getBatteryLevel()).isEqualTo(resource.getBatteryLevel());
		assertThat(resourceDto.getAvailable()).isEqualTo(true);
		assertThat(resourceDto.getCompanyZoneId()).isEqualTo(resource.getCompanyZoneId());
		assertThat(resourceDto.getEngineType()).isEqualTo(resource.getEngineType());
		assertThat(resourceDto.getHelmets()).isEqualTo(resource.getHelmets());
		assertThat(resourceDto.getId()).isEqualTo(resource.getId());
		assertThat(resourceDto.getLicencePlate()).isEqualTo(resource.getLicencePlate());
		assertThat(resourceDto.getModel()).isEqualTo(resource.getModel());
		assertThat(resourceDto.getName()).isEqualTo(resource.getName());
		assertThat(resourceDto.getPricePerMinuteDriving()).isEqualTo(resource.getPricePerMinuteDriving());
		assertThat(resourceDto.getPricePerMinuteParking()).isEqualTo(resource.getPricePerMinuteParking());
		assertThat(resourceDto.getRange()).isEqualTo(resource.getRange());
		assertThat(resourceDto.getRealTimeData()).isEqualTo(resource.getRealTimeData());
		assertThat(resourceDto.getResourceImageId()).isEqualTo(resource.getResourceImageId());
		assertThat(resourceDto.getResourceType()).isEqualTo(resource.getResourceType());
		assertThat(resourceDto.getSeats()).isEqualTo(resource.getSeats());
		assertThat(resourceDto.getX()).isEqualTo(resource.getX());
		assertThat(resourceDto.getY()).isEqualTo(resource.getY());
	}
}
