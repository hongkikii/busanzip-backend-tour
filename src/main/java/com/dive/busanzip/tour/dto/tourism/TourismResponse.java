package com.dive.busanzip.tour.dto.tourism;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TourismResponse {
    private List<Place> course;
    private List<Integer> moveMinutes;

    public static TourismResponse of(List<Place> course, List<Integer> moveMinutes) {
        return new TourismResponse(course, moveMinutes);
    }
}
