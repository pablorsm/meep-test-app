package com.meep.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.meep.test.utils.TestUtils;

public class ResourceDTOTest {
	
	@Test
	public void shouldReturnResourceObject() {
		TestUtils testUtils = new TestUtils();
		ResourceDTO resource = testUtils.createResourceDTO();
		ResourceDTO resourceCopy = resource;
		assertThat(resource.hashCode()).isNotNull();
		assertThat(resource.canEqual(resourceCopy)).isTrue();
		assertThat(resource.equals(resourceCopy)).isTrue();
	}
}
