// TimeTableService.java
package com.project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TimeTableService {

    @Value("${apiTimeTable.key}")
    private String apitimetableKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "https://open.neis.go.kr/hub/hisTimetable";

    public String getTimetable(String officeCode, String schoolCode, String grade, String classNM, String date) {
        String url = BASE_URL +
                "?KEY=" + apitimetableKey +
                "&Type=json" +
                "&ATPT_OFCDC_SC_CODE=" + officeCode +
                "&SD_SCHUL_CODE=" + schoolCode +
                "&GRADE=" + grade +
                "&CLASS_NM=" + classNM +
                "&TI_FROM_YMD=" + date +
                "&TI_TO_YMD=" + date;
        try {
            System.out.println("등교요청: grade=" + grade + ", classNM=" + classNM + ", date=" + date);

            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.out.println("❌ 오류 발생: " + e.getMessage());
            return "오류 발생: " + e.getMessage();
        }

//        return restTemplate.getForObject(url, String.class);
    }
}
