package com.dive.busanzip.tour.dto.tourism;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransitRouteRequest {
    private Point origin;
    private Point destination;
    private String arrivalTime;
    private String travelMode;
    private String languageCode;
    private String units;

    @AllArgsConstructor
    @Getter
    public static class Point {
        private Location location;
    }

    @AllArgsConstructor
    @Getter
    public static class Location {
        private LatLng latLng;
    }

    @AllArgsConstructor
    @Getter
    public static class LatLng {
        private double latitude;
        private double longitude;
    }
}
