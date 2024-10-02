package com.dive.busanzip.tour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    private Long id;
    private TravelType travelType;
    private String name;
    private String address;
    private String phone;
    @Lob
    private String details;
    private Double latitude;
    private Double longitude;
    private String imageUrl;
    private String thumbnailUrl;
    private String homePageUrl;
    private String representativeMenu;
    @Lob
    private String usageTime;

}
