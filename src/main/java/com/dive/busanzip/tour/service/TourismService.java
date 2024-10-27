package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.tourism.CandidateRequest;
import com.dive.busanzip.tour.dto.tourism.CandidateResponse;
import com.dive.busanzip.tour.dto.tourism.CarRouteResponse;
import com.dive.busanzip.tour.dto.tourism.Place;
import com.dive.busanzip.tour.dto.tourism.TourismRequest;
import com.dive.busanzip.tour.dto.tourism.TourismResponse;
import com.dive.busanzip.tour.dto.tourism.TransitRouteRequest;
import com.dive.busanzip.tour.dto.tourism.TransitRouteRequest.LatLng;
import com.dive.busanzip.tour.dto.tourism.TransitRouteRequest.Location;
import com.dive.busanzip.tour.dto.tourism.TransitRouteRequest.Point;
import com.dive.busanzip.tour.dto.tourism.TransitRouteResponse;
import com.dive.busanzip.tour.entity.Accommodation;
import com.dive.busanzip.tour.entity.Experience;
import com.dive.busanzip.tour.entity.Restaurant;
import com.dive.busanzip.tour.entity.Shopping;
import com.dive.busanzip.tour.entity.TouristAttraction;
import com.dive.busanzip.tour.entity.TravelType;
import com.dive.busanzip.tour.repository.AccommodationRepository;
import com.dive.busanzip.tour.repository.ExperienceRepository;
import com.dive.busanzip.tour.repository.RestaurantRepository;
import com.dive.busanzip.tour.repository.ShoppingRepository;
import com.dive.busanzip.tour.repository.TouristAttractionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class TourismService {

    private final RestTemplate restTemplate;
    private static final String CAR_ROUTE_API_URL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";
    private static final String TRANSIT_ROUTE_API_URL = "https://routes.googleapis.com/directions/v2:computeRoutes";

    @Value("${api.route.car.key-id}")
    private String CAR_ROUTE_API_KEY_ID;
    @Value("${api.route.car.key}")
    private String CAR_ROUTE_API_KEY;
    @Value("${api.route.transit.key}")
    private String TRANSIT_API_KEY;


    private final RestaurantRepository restaurantRepository;
    private final ShoppingRepository shoppingRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    private final ExperienceRepository experienceRepository;
    private final AccommodationRepository accommodationRepository;

    public TourismResponse create(TourismRequest request) {
        List<Long> restaurantCandidateIds;
        List<Long> shoppingCandidateIds;
        List<Long> touristAttractionCandidateIds;
        List<Long> experienceCandidateIds;
        int restaurantIdx = 0;
        int shoppingIdx = 0;
        int touristAttractionIdx = 0;
        int experienceIdx = 0;

        List<List<Long>> candidates = getCandidateIds(request);

        restaurantCandidateIds = candidates.get(0);
        shoppingCandidateIds = candidates.get(1);
        touristAttractionCandidateIds = candidates.get(2);
        experienceCandidateIds = candidates.get(3);

        List<List<Long>> finalCandidates = addRestPlace(restaurantCandidateIds, shoppingCandidateIds,
                touristAttractionCandidateIds, experienceCandidateIds);

        restaurantCandidateIds = finalCandidates.get(0);
        shoppingCandidateIds = finalCandidates.get(1);
        touristAttractionCandidateIds = finalCandidates.get(2);
        experienceCandidateIds = finalCandidates.get(3);

        String[] sequence = request.getSequence();
        List<Place> placeList = new ArrayList<>();
        List<Integer> moveMinutes = new ArrayList<>();
        Place lastPlace = null;
        int maxMoveMin = request.getMaxMoveMin();
        boolean isUsingCar = request.getIsUsingCar();

        for (String type : sequence) {
            switch (type) {
                case "RESTAURANT":
                    lastPlace = addPlacesToCourse(placeList, moveMinutes, restaurantCandidateIds, lastPlace, TravelType.RESTAURANT, maxMoveMin, isUsingCar, restaurantIdx);
                    restaurantIdx++;
                    break;
                case "SHOPPING":
                    lastPlace = addPlacesToCourse(placeList, moveMinutes, shoppingCandidateIds, lastPlace, TravelType.SHOPPING, maxMoveMin, isUsingCar, shoppingIdx);
                    shoppingIdx++;
                    break;
                case "TOURIST_ATTRACTION":
                    lastPlace = addPlacesToCourse(placeList, moveMinutes, touristAttractionCandidateIds, lastPlace, TravelType.TOURIST_ATTRACTION, maxMoveMin, isUsingCar, touristAttractionIdx);
                    touristAttractionIdx++;
                    break;
                case "EXPERIENCE":
                    lastPlace = addPlacesToCourse(placeList, moveMinutes, experienceCandidateIds, lastPlace, TravelType.EXPERIENCE, maxMoveMin, isUsingCar, experienceIdx);
                    experienceIdx++;
                    break;
                case "ACCOMMODATION":
                    lastPlace = addAccommodationToCourse(placeList, moveMinutes, lastPlace, request);
                    break;
            }
        }
        return TourismResponse.of(placeList, moveMinutes);
    }

    private List<List<Long>> getCandidateIds(TourismRequest request) {
        String url = "http://.../recommend_tours";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<String> types = new ArrayList<>();
        List<String> requirements = new ArrayList<>();

        if (request.getEatingCount() > 0) {
            types.add("restaurant");
            StringBuilder builder = new StringBuilder();
            for (String eatingType : request.getEatingTypes()) {
                builder.append(eatingType).append(", ");
            }
            builder.append(request.getLikeMenus()).append(", ").append(request.getEatingRequirements());
            String totalEatingRequirements = builder.toString();
            requirements.add(totalEatingRequirements);
        }

        if (request.getShoppingCount() > 0) {
            types.add("shopping");
            requirements.add(request.getShoppingRequirements());
        }

        if (request.getTouristAttractionCount() > 0) {
            types.add("attraction");
            requirements.add(request.getTouristAttractionRequirements());
        }

        if (request.getExperienceCount() > 0) {
            types.add("experience");
            requirements.add(request.getExperienceRequirements());
        }
        CandidateRequest requestBody = new CandidateRequest(types, requirements);

        HttpEntity<CandidateRequest> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<CandidateResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                CandidateResponse.class
        );

        CandidateResponse response = responseEntity.getBody();

        List<List<Long>> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            result.add(new ArrayList<>());
        }
        int tourIdx = 0;
        for (String type : response.getTypes()) {
            if (type.equals("restaurant")) {
                result.get(0).addAll(response.getTours().get(tourIdx++));
            }
            if (type.equals("shopping")) {
                result.get(1).addAll(response.getTours().get(tourIdx++));
            }
            if (type.equals("attraction")) {
                result.get(2).addAll(response.getTours().get(tourIdx++));
            }
            if(type.equals("experience")) {
                result.get(3).addAll(response.getTours().get(tourIdx++));
            }
        }
        return result;
    }

    private List<List<Long>> addRestPlace(List<Long> restaurantCandidateIds, List<Long> shoppingCandidateIds,
                                          List<Long> touristAttractionCandidateIds, List<Long> experienceCandidateIds) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<Shopping> shoppings = shoppingRepository.findAll();
        List<TouristAttraction> attractions = touristAttractionRepository.findAll();
        List<Experience> experiences = experienceRepository.findAll();

        restaurants.stream()
                .map(Restaurant::getId)
                .filter(id -> !restaurantCandidateIds.contains(id))
                .forEach(restaurantCandidateIds::add);

        shoppings.stream()
                .map(Shopping::getId)
                .filter(id -> !shoppingCandidateIds.contains(id))
                .forEach(shoppingCandidateIds::add);

        attractions.stream()
                .map(TouristAttraction::getId)
                .filter(id -> !touristAttractionCandidateIds.contains(id))
                .forEach(touristAttractionCandidateIds::add);

        experiences.stream()
                .map(Experience::getId)
                .filter(id -> !experienceCandidateIds.contains(id))
                .forEach(experienceCandidateIds::add);

        List<List<Long>> result = new ArrayList<>();
        result.add(restaurantCandidateIds);
        result.add(shoppingCandidateIds);
        result.add(touristAttractionCandidateIds);
        result.add(experienceCandidateIds);
        return result;
    }

    private Place addPlacesToCourse(List<Place> placeList, List<Integer> moveMinutes, List<Long> candidateIds, Place lastPlace, TravelType travelType, int maxMoveMin, boolean isUsingCar, int idx) {
        for (int i= idx; i < candidateIds.size(); i++) {
            Place place = getPlaceById(candidateIds.get(i), travelType);

            // 첫 장소인 경우
            if (lastPlace == null) {
                placeList.add(place);
                System.out.println("장소: " + place.getName());
                return place;
            }
            else {
                int travelTime = getTravelTime(lastPlace.getLatitude(), lastPlace.getLongitude(), place.getLatitude(), place.getLongitude(), isUsingCar);
                if (travelTime < maxMoveMin) {
                    placeList.add(place);
                    moveMinutes.add(travelTime);
                    System.out.println("다음 장소: " + place.getName());
                    return place;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private Place addAccommodationToCourse(List<Place> placeList, List<Integer> moveMinutes, Place lastPlace, TourismRequest request) {
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
            int moveMinute = getTravelTime(lastPlace.getLatitude(), lastPlace.getLongitude(), place.getLatitude(),
                    place.getLongitude(), request.getIsUsingCar());
            moveMinutes.add(moveMinute);
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
        String start = startLng + "," + startLat;
        String goal = endLng + "," + endLat;

        if(isUsingCar) {
            String url = UriComponentsBuilder.fromHttpUrl(CAR_ROUTE_API_URL)
                    .queryParam("start", start)
                    .queryParam("goal", goal)
                    .toUriString();

            System.out.println(start);
            System.out.println(goal);

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-NCP-APIGW-API-KEY-ID", CAR_ROUTE_API_KEY_ID);
            headers.set("X-NCP-APIGW-API-KEY", CAR_ROUTE_API_KEY);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<CarRouteResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, CarRouteResponse.class);
            long durationMilSec = response.getBody()
                    .getRoute().getTraoptimal().get(0)
                    .getSummary().getDuration();
            return (int) (durationMilSec / 60000);
        }
        else {
            String fieldMask = "routes.legs.duration";
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Goog-Api-Key", TRANSIT_API_KEY);
            headers.set("X-Goog-FieldMask", fieldMask);

            LatLng startLatLng = new LatLng(startLat, startLng);
            LatLng endLatLng = new LatLng(endLat, endLng);
            Location startLocation = new Location(startLatLng);
            Location endLocation = new Location(endLatLng);

            Point origin = new Point(startLocation);
            Point destination = new Point(endLocation);

            TransitRouteRequest requestBody = new TransitRouteRequest(origin, destination, "2024-10-06T10:00:00Z",
                    "TRANSIT", "en-US", "IMPERIAL");

            HttpEntity<TransitRouteRequest> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<TransitRouteResponse> response = restTemplate.exchange(TRANSIT_ROUTE_API_URL,
                    HttpMethod.POST, entity, TransitRouteResponse.class);

            String duration = response.getBody()
                    .getRoutes().get(0)
                    .getLegs().get(0)
                    .getDuration();

            int sec = Integer.parseInt(duration.replace("s", ""));
            return sec / 60;
        }
    }
}
