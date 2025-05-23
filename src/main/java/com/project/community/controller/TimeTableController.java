// TimeTableController.java
package com.project.community.controller;

import com.project.community.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/timetable")
public class TimeTableController {

    private final TimeTableService timeTableService;

    @GetMapping
    public String getTimetable(
            @RequestParam String grade,
            @RequestParam String classNM,
            @RequestParam String date
    ) {
        String officeCode = "B10";
        String schoolCode = "7010537";
        return timeTableService.getTimetable(officeCode, schoolCode, grade, classNM, date);
    }
}
