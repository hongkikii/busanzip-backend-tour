package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.api.ExperienceData;
import com.dive.busanzip.tour.dto.api.ExperienceReponse;
import com.dive.busanzip.tour.dto.api.RestaurantResponse;
import com.dive.busanzip.tour.dto.api.RestaurantData;
import com.dive.busanzip.tour.dto.api.ShoppingData;
import com.dive.busanzip.tour.dto.api.TouristAttractionData;
import com.dive.busanzip.tour.dto.api.TouristAttractionResponse;
import com.dive.busanzip.tour.entity.Accommodation;
import com.dive.busanzip.tour.entity.ShoppingResponse;
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
import java.time.LocalDate;
import java.util.ArrayList;
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

    // TODO : 필요없는 컬럼 제거
    @Transactional
    public void saveAccommodation() {
        ClassPathResource resource = new ClassPathResource("accommodation.csv");
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream(), Charset.forName("EUC-KR")));
            List<String[]> csvData = reader.readAll();

            for(int i=1; i<csvData.size(); i++) {
                String[] row = csvData.get(i);
                Accommodation accommodation = Accommodation.create(
                        row[0],
                        row[1],
                        row[2],
                        row[3],
                        row[4],
                        row[5],
                        row[6],
                        row[7],
                        row[8],
                        row[9],
                        row[10],
                        row[11],
                        row[12],
                        Double.parseDouble(row[13].replaceAll("^\"|\"$", "")),
                        Double.parseDouble(row[14].replaceAll("^\"|\"$", "")),
                        "Y".equals(row[15]),
                        row[16],
                        row[17],
                        "Y".equals(row[18]),
                        "Y".equals(row[19]),
                        row[20],
                        "Y".equals(row[21]),
                        "Y".equals(row[22]),
                        "Y".equals(row[23]),
                        "Y".equals(row[24]),
                        "Y".equals(row[25]),
                        LocalDate.parse(row[26])
                );
                accommodationRepository.save(accommodation);
            }
        } catch (Exception e) {
            log.error("Error loading accommodation: {}", e.getMessage());
        }
    }
}
