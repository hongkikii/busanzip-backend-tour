package com.dive.busanzip.tour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Shopping {
    @Id
    private Long id;
    private TravelType travelType;
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private String phone;
    private String homePageUrl;
    private String usageDay;
    private String holidayInfo;
    private String usageTime;
    private String usagePrice;
    private String amenities;
    private String imageUrl;
    private String thumbnailUrl;
    private String details;
}
