package com.dive.busanzip.tour.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TravelType {
    RESTAURANT("restaurant"),
    TOURIST_ATTRACTION("tourist_attraction"),
    SHOPPING("shopping"),
    EXPERIENCE("experience"),
    ACCOMMODATION("accommodation");

    private String travelTypeStr;
}
