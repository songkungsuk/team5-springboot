package com.game.team5;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan
@ServletComponentScan //javax 나 fillter같은것을 스캔하기위해 사용
public class Team5Application {

	public static void main(String[] args) {
		SpringApplication.run(Team5Application.class, args);
	}

}
