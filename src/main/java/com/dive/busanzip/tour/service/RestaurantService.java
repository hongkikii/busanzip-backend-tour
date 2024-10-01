package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.RestaurantListData;
import com.dive.busanzip.tour.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class RestaurantService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String serviceKey;
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestTemplate restTemplate, @Value("${baseurl.restaurant}") String baseUrl,
                             @Value("${serviceKey.restaurant}") String serviceKey, RestaurantRepository restaurantRepository) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.serviceKey = serviceKey;
        this.restaurantRepository = restaurantRepository;
    }

    // TODO: 스케줄러 설정
    @Transactional
    public void save() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<RestaurantListData> response = restTemplate.exchange(baseUrl + "serviceKey=" + serviceKey
                + "&numOfRows=396&pageNo=1&resultType=json", HttpMethod.GET, entity, RestaurantListData.class);

        RestaurantListData result = response.getBody();
        System.out.println(result);

//        restaurantListData.getItems().stream()
//                .forEach(restaurantData ->
//                        restaurantRepository.save(restaurantData.toEntity()));
    }
}
