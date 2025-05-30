package com.project.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {
    private String date;        // 날짜 (YYYYMMDD)
    private String menu;        // 식단 내용 (가공된 HTML 제거 텍스트)
}