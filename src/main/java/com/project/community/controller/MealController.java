package com.project.community.controller;

import com.project.community.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meal")
public class MealController {

    private final MealService mealService;

    @GetMapping
    public String getMeal(
            @RequestParam String date
    ) {
        String officeCode = "B10";
        String schoolCode = "7010537";
        return mealService.getMeal(officeCode, schoolCode, date);
    }
}
