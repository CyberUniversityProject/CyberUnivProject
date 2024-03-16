package com.cyber.university.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietTestController {

	@GetMapping("/dietTest")
	public String callApiWithJson() {
		StringBuffer result = new StringBuffer();
		String jsonPrintString = null;
		try {
			String apiUrl = "https://open.neis.go.kr/hub/mealServiceDietInfo?" + 
				"serviceKey=[Service Key]" + 
				"&pSize=1" +
				"&pIndex=10";
			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
			String returnLine;
			while((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine);
			}
			
		
			jsonPrintString.toString();
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return jsonPrintString;
		
	}
	
	
	
	
	// 기본인자 변수 설명, 설명, 기본값
	String KEY; // 인증키 sample key
	String Type; // 호출문서 xml.json // xml
	Integer pIndex; // 페이지 위치  1(sample key는 1 고정)
	Integer pSize; // 페이지당 신청 숫자 

	// 신청인자
	String ATPT_OFCDC_SC_CODE; // 시도교육청코드 필수
	String SD_SCHUL_CODE; //행정표준코드 필수
	String MMEAL_SC_CODE; //식사코드
	String MLSV_YMD;
	String MLSV_FROM_YMD;
	String MLSV_TO_YMD;
	
}
