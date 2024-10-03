package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.course.CourseResponse;
import com.dive.busanzip.tour.dto.course.CourseRequest;
import com.dive.busanzip.tour.dto.course.Place;
import com.dive.busanzip.tour.entity.Accommodation;
import com.dive.busanzip.tour.entity.TravelType;
import com.dive.busanzip.tour.repository.AccommodationRepository;
import com.dive.busanzip.tour.repository.ExperienceRepository;
import com.dive.busanzip.tour.repository.RestaurantRepository;
import com.dive.busanzip.tour.repository.ShoppingRepository;
import com.dive.busanzip.tour.repository.TouristAttractionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final RestaurantRepository restaurantRepository;
    private final ShoppingRepository shoppingRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    private final ExperienceRepository experienceRepository;
    private final AccommodationRepository accommodationRepository;

    public CourseResponse create(CourseRequest request) {
        Map<Integer, Long> restaurantScore = null;
        Map<Integer, Long> shoppingScore = null;
        Map<Integer, Long> touristAttractionScore = null;
        Map<Integer, Long> experienceScore = null;

        // TODO : 요구사항 빈 경우, 요청 X
        if(request.getEatingCount() > 0) {
            restaurantScore = calculateScore(request.getEatingRequirements(), TravelType.RESTAURANT);
        }
        if(request.getShoppingCount() > 0) {
            shoppingScore = calculateScore(request.getShoppingRequirements(), TravelType.SHOPPING);
        }
        if(request.getTouristAttractionCount() > 0) {
            touristAttractionScore = calculateScore(request.getTouristAttractionRequirements(), TravelType.TOURIST_ATTRACTION);
        }
        if(request.getExperienceCount() > 0) {
            experienceScore = calculateScore(request.getExperienceRequirements(), TravelType.EXPERIENCE);
        }

        String[] sequence = request.getSequence();
        List<Place> placeList = new ArrayList<>();
        Place lastPlace = null;
        int maxMoveMin = request.getMaxMoveMin();
        boolean isUsingCar = request.getIsUsingCar();

        for (String type : sequence) {
            switch (type) {
                case "RESTAURANT":
                    lastPlace = addPlacesToCourse(placeList, restaurantScore, lastPlace, TravelType.RESTAURANT, maxMoveMin, isUsingCar);
                    break;
                case "SHOPPING":
                    lastPlace = addPlacesToCourse(placeList, shoppingScore, lastPlace, TravelType.SHOPPING, maxMoveMin, isUsingCar);
                    break;
                case "TOURIST_ATTRACTION":
                    lastPlace = addPlacesToCourse(placeList, touristAttractionScore, lastPlace, TravelType.TOURIST_ATTRACTION, maxMoveMin, isUsingCar);
                    break;
                case "EXPERIENCE":
                    lastPlace = addPlacesToCourse(placeList, experienceScore, lastPlace, TravelType.EXPERIENCE, maxMoveMin, isUsingCar);
                    break;
                case "ACCOMMODATION":
                    lastPlace = addAccommodationToCourse(placeList, lastPlace, request);
                    break;
            }
        }
        return CourseResponse.of(placeList);
    }

    // TODO: 스코어 매기기
    private Map<Integer, Long> calculateScore(String[] eatingRequirements, TravelType travelType) {
        Map<Integer, Long> score = new ConcurrentHashMap<>();
        if(travelType == TravelType.RESTAURANT) {
            score.put(1, 70L);
            score.put(2, 72L);
            score.put(3, 77L);
            score.put(4, 83L);
            score.put(5, 87L);
        }
        if(travelType == TravelType.SHOPPING) {
            score.put(1, 292L);
            score.put(2, 293L);
            score.put(3, 294L);
            score.put(4, 300L);
            score.put(5, 327L);
        }
        if(travelType == TravelType.TOURIST_ATTRACTION) {
            score.put(1, 255L);
            score.put(2, 256L);
            score.put(3, 257L);
            score.put(4, 258L);
            score.put(5, 259L);
        }
        if(travelType == TravelType.EXPERIENCE) {
            score.put(1, 43L);
            score.put(2, 44L);
            score.put(3, 139L);
            score.put(4, 140L);
            score.put(5, 336L);
        }
        return score;
    }

    private Place addPlacesToCourse(List<Place> placeList, Map<Integer, Long> scoreMap, Place lastPlace, TravelType travelType, int maxMoveMin, boolean isUsingCar) {
        for (Map.Entry<Integer, Long> entry : scoreMap.entrySet()) {
            Long placeId = entry.getValue();
            Place place = getPlaceById(placeId, travelType);

            // 첫 장소인 경우
            if (lastPlace == null) {
                placeList.add(place);
                scoreMap.remove(entry.getKey());
                return place;
            }
            else {
                double travelTime = getTravelTime(lastPlace.getLatitude(), lastPlace.getLongitude(), place.getLatitude(), place.getLongitude(), isUsingCar);
                if (travelTime < maxMoveMin) {
                    placeList.add(place);
                    scoreMap.remove(entry.getKey());
                    return place;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private Place addAccommodationToCourse(List<Place> placeList, Place lastPlace, CourseRequest request) {
        String[] keywords = request.getAccommodationKeywords();
        Place place = null;
        double latitude;
        double longitude;
        // 첫 장소인 경우
        if(lastPlace == null) {
            place = getFirstAccommodation(keywords);
        }
        else {
            latitude = lastPlace.getLatitude();
            longitude = lastPlace.getLongitude();
            Accommodation accommodation = accommodationRepository.findNearestByKeyword(latitude, longitude, keywords[0]).orElseThrow();
            place = getPlaceById(accommodation.getId(), TravelType.ACCOMMODATION);
        }
        placeList.add(place);
        return place;
    }

    private Place getFirstAccommodation(String[] keywords) {
        List<Accommodation> accommodations = new ArrayList<>();

        if(keywords.length == 0) {
            List<Accommodation> all = accommodationRepository.findAll();
            accommodations.addAll(all);
        }
        else {
            for(String keyword : keywords) {
                List<Accommodation> allByKeyword = accommodationRepository.findByKeyword(keyword);
                accommodations.addAll(allByKeyword);
            }
        }
        Random random = new Random();
        int randomIdx = random.nextInt(accommodations.size());
        return Place.from(accommodations.get(randomIdx));
    }

    private Place getPlaceById(Long id, TravelType travelType) {
        if(travelType == TravelType.RESTAURANT) {
            return Place.from(restaurantRepository.findById(id).orElseThrow());
        }
        if(travelType == TravelType.SHOPPING) {
            return Place.from(shoppingRepository.findById(id).orElseThrow());
        }
        if(travelType == TravelType.TOURIST_ATTRACTION) {
            return Place.from(touristAttractionRepository.findById(id).orElseThrow());
        }
        if(travelType == TravelType.EXPERIENCE) {
            return Place.from(experienceRepository.findById(id).orElseThrow());
        }
        if(travelType == TravelType.ACCOMMODATION) {
            return Place.from(accommodationRepository.findById(id).orElseThrow());
        }
        throw new IllegalArgumentException();
    }

    private int getTravelTime(Double startLat, Double startLng, Double endLat, Double endLng, boolean isUsingCar) {
        // TODO 외부 Api 호출
        int travelTime = Integer.MIN_VALUE;

        if(isUsingCar) {

        }
        else {

        }
        return 30;
    }
}
