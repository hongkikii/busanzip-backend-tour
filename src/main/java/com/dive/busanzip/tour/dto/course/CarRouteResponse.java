package com.dive.busanzip.tour.dto.course;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class CarRouteResponse {

    private int code;
    private String message;
    private LocalDateTime currentDateTime;
    private Route route;

    @Getter
    public static class Route {
        private List<Traoptimal> traoptimal;

        @Getter
        public static class Traoptimal {
            private Summary summary;
            private List<List<Double>> path;
            private List<Section> section;
            private List<Guide> guide;

            @Getter
            public static class Summary {
                private Location start;
                private Location goal;
                private long distance;
                private long duration;
                private LocalDateTime departureTime;
                private List<List<Double>> bbox;
                private long tollFare;
                private long taxiFare;
                private long fuelPrice;
            }

            @Getter
            public static class Location {
                private List<Double> location;
            }

            @Getter
            public static class Section {
                private int pointIndex;
                private int pointCount;
                private long distance;
                private String name;
                private int congestion;
                private int speed;
            }

            @Getter
            public static class Guide {
                private int pointIndex;
                private int type;
                private String instructions;
                private long distance;
                private long duration;
            }
        }
    }
}
