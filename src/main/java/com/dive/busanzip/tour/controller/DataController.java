package com.dive.busanzip.tour.controller;

import com.dive.busanzip.tour.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final ApiService apiService;

    @PostMapping("/restaurant")
    public HttpStatus saveRestaurantData() {
        apiService.saveRestaurant();
        return HttpStatus.OK;
    }

    @PostMapping("/tourist-attraction")
    public HttpStatus saveTouristAttractionData() {
        apiService.saveTouristAttraction();
        return HttpStatus.OK;
    }

    @PostMapping("/shopping")
    public HttpStatus saveShoppingData() {
        apiService.saveShopping();
        return HttpStatus.OK;
    }

    @PostMapping("/experience")
    public HttpStatus saveExperienceData() {
        apiService.saveExperience();
        return HttpStatus.OK;
    }

    @PostMapping("/accommodation")
    public HttpStatus saveAccommodationData() {
        apiService.saveAccommodation();
        return HttpStatus.OK;
    }
}
