package kr.co.kmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"kr.co.kmarket.dao", "kr.co.kmarket.admin.dao"}) //쿼리문 함수들 수행하기 위한 연동작업
@SpringBootApplication
public class KmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmarketApplication.class, args);
	}

}
