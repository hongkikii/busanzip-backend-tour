package com.dive.busanzip.tour.controller;

import com.dive.busanzip.tour.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final RestaurantService restaurantService;

    @PostMapping("/api/restaurant")
    public HttpStatus saveRestaurantData() {
        restaurantService.save();
        return HttpStatus.OK;
    }
}
