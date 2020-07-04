package br.com.lojavirtual;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LojaVirtualApplication.class)
@TestPropertySource(locations="classpath:test.properties")
class LojaVirtualApplicationTests {

	@Test
	void contextLoads() {
	}

}
