package com.cyber.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class CyberUniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyberUniversityApplication.class, args);
	}

	
	   // hidden 속성 사용
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
