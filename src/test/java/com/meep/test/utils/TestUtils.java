package com.meep.test.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meep.domain.Resource;
import com.meep.dto.ResourceDTO;

public class TestUtils {
	
	public List<Resource> getJsonFileAsList(String filename) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("json/"+filename).getFile());
        return mapper.readValue(FileUtils.readFileToString(file, Charset.defaultCharset()), new TypeReference<List<Resource>>(){});
    }
	
	public final Resource createResource() {
		return Resource.builder()
				.batteryLevel(1)
				.companyZoneId(1)
				.engineType("engineType")
				.helmets(2)
				.id(UUID.randomUUID().toString())
				.licencePlate("licencePlate")
				.model("model")
				.name("name")
				.name("name")
				.pricePerMinuteDriving(15.0)
				.pricePerMinuteParking(16.0)
				.range(34)
				.realTimeData(true)
				.resourceImageId("resourceImageId")
				.resourceType("resourceType")
				.seats(2)
				.x(12.0)
				.y(10.0)
				.build();
	}
	
	public final ResourceDTO createResourceDTO() {
		return ResourceDTO.builder()
				.batteryLevel(1)
				.companyZoneId(1)
				.engineType("engineType")
				.helmets(2)
				.id(UUID.randomUUID().toString())
				.licencePlate("licencePlate")
				.model("model")
				.name("name")
				.name("name")
				.pricePerMinuteDriving(15.0)
				.pricePerMinuteParking(16.0)
				.range(34)
				.realTimeData(true)
				.resourceImageId("resourceImageId")
				.resourceType("resourceType")
				.seats(2)
				.x(12.0)
				.y(10.0)
				.available(true)
				.build();
	}
	
}
