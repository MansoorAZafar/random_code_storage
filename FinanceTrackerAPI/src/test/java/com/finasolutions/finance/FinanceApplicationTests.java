package com.finasolutions.finance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class FinanceApplicationTests {

	@Test
	void contextLoads() {
	}

}
