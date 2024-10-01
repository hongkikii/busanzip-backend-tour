package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.api.RestaurantResponse;
import com.dive.busanzip.tour.dto.api.RestaurantData;
import com.dive.busanzip.tour.dto.api.TouristAttractionData;
import com.dive.busanzip.tour.dto.api.TouristAttractionResponse;
import com.dive.busanzip.tour.repository.RestaurantRepository;
import com.dive.busanzip.tour.repository.TouristAttractionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ApiService {

    private final RestaurantRepository restaurantRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public ApiService(RestaurantRepository restaurantRepository,
                      TouristAttractionRepository touristAttractionRepository,
                      ResourceLoader resourceLoader) {
        this.restaurantRepository = restaurantRepository;
        this.touristAttractionRepository = touristAttractionRepository;
        this.resourceLoader = resourceLoader;
        this.objectMapper = new ObjectMapper();
    }

    // TODO: 외부 api 호출해서 정기 추출
    @Transactional
    public void saveRestaurant() {
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

    public void saveTouristAttraction() {
        Resource resource = resourceLoader.getResource("classpath:tourist-attraction.json");
        try {
            TouristAttractionResponse response = objectMapper.readValue(resource.getInputStream(),
                    TouristAttractionResponse.class);
            TouristAttractionData[] touristAttractionArray = response.getItem();
            for(TouristAttractionData touristAttractionData: touristAttractionArray) {
                touristAttractionRepository.save(touristAttractionData.toEntity());
            }
        } catch (IOException e) {
            log.error("Error loading tourist-attractions: {}", e.getMessage());
        }
    }

    public void saveShopping() {
        Resource resource = resourceLoader.getResource("classpath:shopping.json");
        try {
            RestaurantResponse response = objectMapper.readValue(resource.getInputStream(), RestaurantResponse.class);
            RestaurantData[] restaurantArray = response.getItem();
            for(RestaurantData restaurantData: restaurantArray) {
                restaurantRepository.save(restaurantData.toEntity());
            }
        } catch (IOException e) {
            log.error("Error loading shopping: {}", e.getMessage());
        }
    }

    public void saveExperience() {
        Resource resource = resourceLoader.getResource("classpath:experience.json");
        try {
            RestaurantResponse response = objectMapper.readValue(resource.getInputStream(), RestaurantResponse.class);
            RestaurantData[] restaurantArray = response.getItem();
            for(RestaurantData restaurantData: restaurantArray) {
                restaurantRepository.save(restaurantData.toEntity());
            }
        } catch (IOException e) {
            log.error("Error loading experience: {}", e.getMessage());
        }
    }
}
