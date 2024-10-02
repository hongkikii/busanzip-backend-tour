package com.dive.busanzip.tour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Experience {

    @Id
    private Long id;
    private TravelType travelType;
    private String name;
    private Double latitude;
    private Double longitude;
    private String title;
    private String subTitle;
    private String address;
    private String phone;
    private String homePageUrl;
    private String usageDay;
    private String holidayInfo;
    @Lob
    private String usageTime;
    private String usagePrice;
    private String amenities;
    private String imageUrl;
    private String thumbnailUrl;
    @Lob
    private String details;

}
