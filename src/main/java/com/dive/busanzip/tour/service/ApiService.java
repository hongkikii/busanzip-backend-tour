package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.data.ExperienceData;
import com.dive.busanzip.tour.dto.data.ExperienceReponse;
import com.dive.busanzip.tour.dto.data.RestaurantResponse;
import com.dive.busanzip.tour.dto.data.RestaurantData;
import com.dive.busanzip.tour.dto.data.ShoppingData;
import com.dive.busanzip.tour.dto.data.TouristAttractionData;
import com.dive.busanzip.tour.dto.data.TouristAttractionResponse;
import com.dive.busanzip.tour.entity.Accommodation;
import com.dive.busanzip.tour.dto.data.ShoppingResponse;
import com.dive.busanzip.tour.repository.AccommodationRepository;
import com.dive.busanzip.tour.repository.ExperienceRepository;
import com.dive.busanzip.tour.repository.RestaurantRepository;
import com.dive.busanzip.tour.repository.ShoppingRepository;
import com.dive.busanzip.tour.repository.TouristAttractionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
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
    private final AccommodationRepository accommodationRepository;

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

    @Transactional
    public void saveAccommodation() {
        ClassPathResource resource = new ClassPathResource("accommodation.csv");
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream(), Charset.forName("EUC-KR")));
            List<String[]> csvData = reader.readAll();

            for(int i=1; i<csvData.size(); i++) {
                String[] row = csvData.get(i);
                if(row[16].equals("")) continue;
                Accommodation accommodation = Accommodation.create(
                        Long.valueOf(row[0]),
                        row[1],
                        row[2],
                        row[3],
                        row[4],
                        Double.parseDouble(row[5]),
                        Double.parseDouble(row[6]),
                        row[7],
                        row[8],
                        "Y".equals(row[9]),
                        "Y".equals(row[10]),
                        "Y".equals(row[11]),
                        "Y".equals(row[12]),
                        "Y".equals(row[13]),
                        "Y".equals(row[14]),
                        "Y".equals(row[15]),
                        row[16]
                );
                accommodationRepository.save(accommodation);
            }
        } catch (Exception e) {
            log.error("Error loading accommodation: {}", e.getMessage());
        }
    }
}
