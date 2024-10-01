package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.api.ExperienceData;
import com.dive.busanzip.tour.dto.api.ExperienceReponse;
import com.dive.busanzip.tour.dto.api.RestaurantResponse;
import com.dive.busanzip.tour.dto.api.RestaurantData;
import com.dive.busanzip.tour.dto.api.ShoppingData;
import com.dive.busanzip.tour.dto.api.TouristAttractionData;
import com.dive.busanzip.tour.dto.api.TouristAttractionResponse;
import com.dive.busanzip.tour.entity.ShoppingResponse;
import com.dive.busanzip.tour.repository.ExperienceRepository;
import com.dive.busanzip.tour.repository.RestaurantRepository;
import com.dive.busanzip.tour.repository.ShoppingRepository;
import com.dive.busanzip.tour.repository.TouristAttractionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {

    private final RestaurantRepository restaurantRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    private final ShoppingRepository shoppingRepository;
    private final ExperienceRepository experienceRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @Transactional
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

    @Transactional
    public void saveShopping() {
        Resource resource = resourceLoader.getResource("classpath:shopping.json");
        try {
            ShoppingResponse response = objectMapper.readValue(resource.getInputStream(), ShoppingResponse.class);
            ShoppingData[] shoppingArray = response.getItem();
            for(ShoppingData shoppingData: shoppingArray) {
                shoppingRepository.save(shoppingData.toEntity());
            }
        } catch (IOException e) {
            log.error("Error loading shopping: {}", e.getMessage());
        }
    }

    @Transactional
    public void saveExperience() {
        Resource resource = resourceLoader.getResource("classpath:experience.json");
        try {
            ExperienceReponse response = objectMapper.readValue(resource.getInputStream(), ExperienceReponse.class);
            ExperienceData[] experienceArray = response.getItem();
            for(ExperienceData experienceData: experienceArray) {
                experienceRepository.save(experienceData.toEntity());
            }
        } catch (IOException e) {
            log.error("Error loading experience: {}", e.getMessage());
        }
    }
}
