package com.dive.busanzip.tour.dto.tourism;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CandidateRequest {
    private List<String> types;
    private List<String> requirements;
}
