package com.fedex.failovercontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
		properties = {
				"management.info.env.enabled=true" ,
				"management.endpoints.web.exposure.include=info"
		})
@AutoConfigureMockMvc
class FailoverControllerApplicationTest {

	@MockBean
	HealthChecker healthChecker;

	@MockBean
	HealthStatusRepository healthStatusRepository;

	@Autowired
	MockMvc mockMvc;

	@Test
	void getActuatorInfo() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
						.get("/actuator/info")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.health").exists());
	}

}
