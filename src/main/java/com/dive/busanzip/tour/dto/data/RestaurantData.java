package com.dive.busanzip.tour.dto.data;

import com.dive.busanzip.tour.entity.Restaurant;
import com.dive.busanzip.tour.entity.TravelType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RestaurantData {
    @JsonProperty("UC_SEQ")
    private Integer UC_SEQ;

    @JsonProperty("MAIN_TITLE")
    private String MAIN_TITLE;

    @JsonProperty("GUGUN_NM")
    private String GUGUN_NM;

    @JsonProperty("LAT")
    private String LAT;

    @JsonProperty("LNG")
    private String LNG;

    @JsonProperty("PLACE")
    private String PLACE;

    @JsonProperty("TITLE")
    private String TITLE;

    @JsonProperty("SUBTITLE")
    private String SUBTITLE;

    @JsonProperty("ADDR1")
    private String ADDR1;

    @JsonProperty("ADDR2")
    private String ADDR2;

    @JsonProperty("CNTCT_TEL")
    private String CNTCT_TEL;

    @JsonProperty("HOMEPAGE_URL")
    private String HOMEPAGE_URL;

    @JsonProperty("USAGE_DAY_WEEK_AND_TIME")
    private String USAGE_DAY_WEEK_AND_TIME;

    @JsonProperty("RPRSNTV_MENU")
    private String RPRSNTV_MENU;

    @JsonProperty("MAIN_IMG_NORMAL")
    private String MAIN_IMG_NORMAL;

    @JsonProperty("MAIN_IMG_THUMB")
    private String MAIN_IMG_THUMB;

    @JsonProperty("ITEMCNTNTS")
    private String ITEMCNTNTS;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .id(Long.valueOf(UC_SEQ))
                .travelType(TravelType.RESTAURANT)
                .name(MAIN_TITLE)
                .address(ADDR1)
                .phone(CNTCT_TEL)
                .details(ITEMCNTNTS)
                .latitude(Double.valueOf(LAT))
                .longitude(Double.valueOf(LNG))
                .homePageUrl(HOMEPAGE_URL)
                .imageUrl(MAIN_IMG_NORMAL)
                .thumbnailUrl(MAIN_IMG_THUMB)
                .representativeMenu(RPRSNTV_MENU)
                .usageTime(USAGE_DAY_WEEK_AND_TIME)
                .build();
    }
}
