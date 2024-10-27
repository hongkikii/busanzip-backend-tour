package com.dive.busanzip.tour.dto.data;

import com.dive.busanzip.tour.entity.Shopping;
import com.dive.busanzip.tour.entity.TravelType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ShoppingData {
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

    @JsonProperty("MAIN_PLACE")
    private String MAIN_PLACE;

    @JsonProperty("ADDR1")
    private String ADDR1;

    @JsonProperty("ADDR2")
    private String ADDR2;

    @JsonProperty("CNTCT_TEL")
    private String CNTCT_TEL;

    @JsonProperty("HOMEPAGE_URL")
    private String HOMEPAGE_URL;

    @JsonProperty("TRFC_INFO")
    private String TRFC_INFO;

    @JsonProperty("USAGE_DAY")
    private String USAGE_DAY;

    @JsonProperty("HLDY_INFO")
    private String HLDY_INFO;

    @JsonProperty("USAGE_DAY_WEEK_AND_TIME")
    private String USAGE_DAY_WEEK_AND_TIME;

    @JsonProperty("USAGE_AMOUNT")
    private String USAGE_AMOUNT;

    @JsonProperty("MIDDLE_SIZE_RM1")
    private String MIDDLE_SIZE_RM1;

    @JsonProperty("MAIN_IMG_NORMAL")
    private String MAIN_IMG_NORMAL;

    @JsonProperty("MAIN_IMG_THUMB")
    private String MAIN_IMG_THUMB;

    @JsonProperty("ITEMCNTNTS")
    private String ITEMCNTNTS;

    public Shopping toEntity() {
        return Shopping.builder()
                .id(Long.valueOf(UC_SEQ))
                .travelType(TravelType.SHOPPING)
                .name(PLACE)
                .latitude(Double.valueOf(LAT))
                .longitude(Double.valueOf(LNG))
                .address(ADDR1)
                .phone(CNTCT_TEL)
                .homePageUrl(HOMEPAGE_URL)
                .usageDay(USAGE_DAY)
                .holidayInfo(HLDY_INFO)
                .usageTime(USAGE_DAY_WEEK_AND_TIME)
                .usagePrice(USAGE_AMOUNT)
                .amenities(MIDDLE_SIZE_RM1)
                .imageUrl(MAIN_IMG_NORMAL)
                .thumbnailUrl(MAIN_IMG_THUMB)
                .details(ITEMCNTNTS)
                .build();
    }
}
