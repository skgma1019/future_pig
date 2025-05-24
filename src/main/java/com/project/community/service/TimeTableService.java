// TimeTableService.java
package com.project.community.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.community.dto.TimeTableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableService {

    @Value("${apiTimeTable.key}")
    private String apitimetableKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "https://open.neis.go.kr/hub/hisTimetable";

    public List<TimeTableDto> getTimetable(String officeCode, String schoolCode, String grade, String classNM, String date) {
        String url = BASE_URL +
                "?KEY=" + apitimetableKey +
                "&Type=json" +
                "&ATPT_OFCDC_SC_CODE=" + officeCode +
                "&SD_SCHUL_CODE=" + schoolCode +
                "&GRADE=" + grade +
                "&CLASS_NM=" + classNM +
                "&TI_FROM_YMD=" + date +
                "&TI_TO_YMD=" + date;

        List<TimeTableDto> result = new ArrayList<>();
        try {
            String json = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            JsonNode rowNode = root.path("hisTimetable").get(1).path("row");
            for (JsonNode node : rowNode) {
                String period = node.get("PERIO").asText();
                String subject = node.get("ITRT_CNTNT").asText();
                result.add(new TimeTableDto(period, subject));
            }
        } catch (Exception e) {
            System.out.println("시간표 파싱 오류: " + e.getMessage());
        }
        return result;
    }
}
