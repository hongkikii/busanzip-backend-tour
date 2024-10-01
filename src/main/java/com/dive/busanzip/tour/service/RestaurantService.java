package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.RestaurantResponse;
import com.dive.busanzip.tour.dto.RestaurantData;
import com.dive.busanzip.tour.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, ResourceLoader resourceLoader) {
        this.restaurantRepository = restaurantRepository;
        this.resourceLoader = resourceLoader;
        this.objectMapper = new ObjectMapper();
    }

    // TODO: 외부 api 호출해서 정기 추출
    @Transactional
    public void save() {
        Resource resource = resourceLoader.getResource("classpath:restaurant.json");
        try {
            RestaurantResponse response = objectMapper.readValue(resource.getInputStream(), RestaurantResponse.class);
            RestaurantData[] restaurantArray = response.getItem();
            for(RestaurantData restaurantData: restaurantArray) {
                restaurantRepository.save(restaurantData.toEntity());
            }
        } catch (IOException e) {
            log.error("Error loading restaurants: {}", e.getMessage());
        }
    }
}
