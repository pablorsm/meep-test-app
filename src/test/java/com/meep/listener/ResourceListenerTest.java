package com.meep.listener;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.meep.service.ResourceService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class ResourceListenerTest {

	@Autowired
	ResourceService resourceService;

	@Test
	public void shouldDetectChanges() {
		ResourceListener rl = new ResourceListener(resourceService);
		rl.detectChanges();
		assertThat(rl.getModifiedResources()).isNotEmpty();
	}
}
