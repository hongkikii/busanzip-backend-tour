package com.dive.busanzip.tour.dto.course;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CourseResponse {
    private List<Place> course;

    public static CourseResponse of(List<Place> course) {
        return new CourseResponse(course);
    }
}
