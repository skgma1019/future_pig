package com.project.community.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.community.dto.MealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    @Value("${apiMeal.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://open.neis.go.kr/hub/mealServiceDietInfo";

    public List<MealDto> getMeal(String officeCode, String schoolCode, String date) {
        String url = BASE_URL +
                "?KEY=" + apiKey +
                "&Type=json" +
                "&pIndex=1" +
                "&pSize=100" +
                "&ATPT_OFCDC_SC_CODE=" + officeCode +
                "&SD_SCHUL_CODE=" + schoolCode +
                "&MLSV_YMD=" + date;
        List<MealDto> result = new ArrayList<>();

        try {
            String json = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode rows = root.path("mealServiceDietInfo").get(1).path("row");

            for (JsonNode row : rows) {
                String mealType = row.get("MMEAL_SC_NM").asText();
                if (!mealType.equals("중식")) continue; // ✅ 중식만

                String menuRaw = row.get("DDISH_NM").asText();
                String menu = menuRaw
                        .replaceAll("\\([0-9.]+\\)", "")       // ✅ (숫자.숫자) 패턴 제거
                        .replaceAll("<br/>", "<br/>")            // ✅ 줄바꿈 → 쉼표+공백
                        .replaceAll(",\\s*,", ", ")           // ✅ 중복 쉼표 제거
                        .trim();                              // ✅ 앞뒤 공백 제거

                String mealDate = row.get("MLSV_YMD").asText();

                result.add(new MealDto(mealDate, menu));
            }

        } catch (Exception e) {
            System.out.println("급식 파싱 오류: " + e.getMessage());
        }

        return result;
    }
}

