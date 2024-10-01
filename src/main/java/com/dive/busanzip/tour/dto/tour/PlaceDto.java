package com.dive.busanzip.tour.dto.tour;

import lombok.Getter;

@Getter
public class PlaceDto {

    private String type;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
//    private ?? usageDay;
//    private ?? holiday;
//    private ?? usageTime;
    private String imageURL;
    private String usageAmount;
    private String contact;
    private String repMenu;
}
