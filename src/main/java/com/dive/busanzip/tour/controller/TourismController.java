package com.dive.busanzip.tour.controller;

import com.dive.busanzip.tour.dto.tourism.TourismRequest;
import com.dive.busanzip.tour.dto.tourism.TourismResponse;
import com.dive.busanzip.tour.service.TourismService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tourism")
@RequiredArgsConstructor
public class TourismController {

    private final TourismService tourismService;

    @PostMapping
    public ResponseEntity<TourismResponse> create(@RequestBody TourismRequest request) {
        return ResponseEntity.ok(tourismService.create(request));
    }
}
