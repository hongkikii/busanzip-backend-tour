package com.dive.busanzip.tour.dto;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RestaurantListData {
    private String numOfRows;
    private String resultCode;
    private String resultMsg;
    private String pageNo;
    private String totalCount;
    private List<RestaurantData> items;
}
