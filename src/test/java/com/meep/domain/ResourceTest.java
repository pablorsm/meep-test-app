package com.meep.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.meep.test.utils.TestUtils;

public class ResourceTest {

	@Test
	public void shouldReturnResourceObject() {
		TestUtils testUtils = new TestUtils();
		Resource resource = testUtils.createResource();
		Resource resourceCopy = resource;
		assertThat(resource.hashCode()).isNotNull();
		assertThat(resource.canEqual(resourceCopy)).isTrue();
		assertThat(resource.equals(resourceCopy)).isTrue();
	}
}
