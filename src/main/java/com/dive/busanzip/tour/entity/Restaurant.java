package com.dive.busanzip.tour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    public Long id;
    public TravelType travelType;
    public String name;
    public String address;
    public String phone;
    public String details;
    public Double latitude;
    public Double longitude;
    public String imageUrl;
    public String thumbnailUrl;
    public String homePageUrl;
    public String representativeMenu;
    public String usageTime;

}
