package com.dive.busanzip.tour.dto.tourism;

import java.util.List;
import lombok.Getter;

@Getter
public class TransitRouteResponse {
    private List<Leg> routes;

    @Getter
    public static class Leg {
        private List<Duration> legs;

        @Getter
        public static class Duration {
            private String duration;
        }
    }
}
