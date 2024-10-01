package com.dive.busanzip.tour.dto.tour;

import lombok.Getter;

@Getter
public class CourseInfo {
    private Integer[] section;
    private Boolean isUsingCar;
    private Integer maxMoveMin;
    private Boolean isAccommodationNeeded;
    private Boolean isAccommodationFirst;
    private Double accommodationLat;
    private Double accommodationLng;
    private Integer eatCount;
    private Integer touristAttractionCount;
    private Integer shoppingCount;
    private Integer experienceCount;
    private String[] sequence;
}
