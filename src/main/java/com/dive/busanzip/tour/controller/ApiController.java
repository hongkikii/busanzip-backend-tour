package com.dive.busanzip.tour.controller;

import com.dive.busanzip.tour.dto.Course;
import com.dive.busanzip.tour.dto.CourseInfo;
import com.dive.busanzip.tour.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/api")
    public ResponseEntity<Course> getCourse(CourseInfo info) {
        return ResponseEntity.ok(apiService.getcourse(info));
    }
}
