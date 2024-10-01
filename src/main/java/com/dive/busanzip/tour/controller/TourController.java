package com.dive.busanzip.tour.controller;

import com.dive.busanzip.tour.dto.tour.Course;
import com.dive.busanzip.tour.dto.tour.CourseInfo;
import com.dive.busanzip.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TourController {

    private final TourService apiService;

    @GetMapping("/api")
    public ResponseEntity<Course> getCourse(CourseInfo info) {
        return ResponseEntity.ok(apiService.getcourse(info));
    }
}
