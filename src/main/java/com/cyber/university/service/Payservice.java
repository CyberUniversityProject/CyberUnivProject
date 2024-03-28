package com.cyber.university.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Payservice {
	
	
	public ResponseEntity<String> getToken() {
	
		// IAMPORT API의 엑세스 키와 시크릿 키
		String impKey = "5203272488443725";
		String impSecret = "Bv9MCQYkJvAj8m9Cem6G0zfErObGGuFbYyN18LDghUuAYnAAYhhkjT2Bi3RcoJNId20gCmld29cxsZL0";
		
		// IAMPORT API 요청 URL
        String apiUrl = "https://api.iamport.kr/users/getToken";
		
		// IAMPORT API 요청 시 전송할 데이터
        String requestData = "{\"imp_key\": \"" + impKey + "\", \"imp_secret\": \"" + impSecret + "\"}"; 
	
        // RestTemplate을 사용하여 POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestData, headers);
        
        // 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

      
        
        return responseEntity;
	}
}
