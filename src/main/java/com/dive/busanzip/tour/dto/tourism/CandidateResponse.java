package com.dive.busanzip.tour.dto.tourism;

import java.util.List;
import lombok.Getter;

@Getter
public class CandidateResponse {
    private List<List<Long>> tours;
    private List<String> types;
}
