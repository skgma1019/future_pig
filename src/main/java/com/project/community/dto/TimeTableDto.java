package com.project.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableDto {
    private String period;   // 교시 (ex. "1")
    private String subject;  // 수업 내용 (ex. "국어")
}
