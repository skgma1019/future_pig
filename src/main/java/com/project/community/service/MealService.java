package com.project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MealService {

    @Value("${apiMeal.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://open.neis.go.kr/hub/mealServiceDietInfo";

    public String getMeal(String officeCode, String schoolCode, String date) {
        String url = BASE_URL +
                "?KEY=" + apiKey +
                "&Type=json" +
                "&pIndex=1" +
                "&pSize=100" +
                "&ATPT_OFCDC_SC_CODE=" + officeCode +
                "&SD_SCHUL_CODE=" + schoolCode +
                "&MLSV_YMD=" + date;

        System.out.println("✅ 급식 API 요청 URL: " + url);
        return restTemplate.getForObject(url, String.class);
    }
}

