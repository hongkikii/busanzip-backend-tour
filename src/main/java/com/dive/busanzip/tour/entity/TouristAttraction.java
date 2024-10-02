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
public class TouristAttraction {

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
    private String imageUrl;
    private String thumbnailUrl;
    private String homePageUrl;
    @Lob
    private String usageTime;
    private String usageDay;
    private String holidayInfo;
    private String usagePrice;
    private String amenities;
    @Lob
    private String details;

}
