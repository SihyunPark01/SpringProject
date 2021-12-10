package kr.co.kmarket;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggingTest {
	
	public void logTest1() {
		System.out.println("로그출력1");
	}
	
	@Test      //실행하고자 하는 놈에만 test 어노테이션 붙이기
	public void logTest2() {
		Logger logger = LoggerFactory.getLogger(this.getClass());

		logger.trace("로그 - trace");
		logger.debug("로그 - debug");
		logger.info("로그 - info");
		logger.warn("로그 - warn");
		logger.error("로그 - error");
	
	}
	
	
}
