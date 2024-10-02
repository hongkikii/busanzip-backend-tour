package com.dive.busanzip.tour.controller;

import com.dive.busanzip.tour.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/api/restaurant")
    public HttpStatus saveRestaurantData() {
        apiService.saveRestaurant();
        return HttpStatus.OK;
    }

    @PostMapping("/api/tourist-attraction")
    public HttpStatus saveTouristAttractionData() {
        apiService.saveTouristAttraction();
        return HttpStatus.OK;
    }

    @PostMapping("/api/shopping")
    public HttpStatus saveShoppingData() {
        apiService.saveShopping();
        return HttpStatus.OK;
    }

    @PostMapping("/api/experience")
    public HttpStatus saveExperienceData() {
        apiService.saveExperience();
        return HttpStatus.OK;
    }

    @PostMapping("/api/accommodation")
    public HttpStatus saveAccommodationData() {
        apiService.saveAccommodation();
        return HttpStatus.OK;
    }
}
