package edu.limecats.sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("메인 함수 테스트")
	void testMain(){
		SampleApplication.main(new String[]{});
	}

}
