package com.dive.busanzip.tour.dto;

import com.dive.busanzip.tour.entity.Restaurant;
import com.dive.busanzip.tour.entity.TravelType;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RestaurantData {
    private String UC_SEQ;
    private String MAIN_TITLE;
    private String GUGUN_NM;
    private String LAT;
    private String LNG;
    private String PLACE;
    private String TITLE;
    private String SUBTITLE;
    private String ADD1;
    private String ADD2;
    private String CNTCT_TEL;
    private String HOMEPAGE_URL;
    private String USAGE_DAY_WEEK_AND_TIME;
    private String RPRSNTV_MENU;
    private String MAIN_IMG_NORMAL;
    private String MAIN_IMG_THUMB;
    private String ITEMCNTNTS;

    public Restaurant toEntity() {

        LocalTime[] usageTimes = getUsageTime(USAGE_DAY_WEEK_AND_TIME);

        return Restaurant.builder()
                .id(Long.valueOf(UC_SEQ))
                .travelType(TravelType.RESTAURANT)
                .name(MAIN_TITLE)
                .address(ADD1)
                .phoneNumber(CNTCT_TEL)
                .details(ITEMCNTNTS)
                .latitude(Double.valueOf(LAT))
                .longitude(Double.valueOf(LNG))
                .imageUrl(MAIN_IMG_NORMAL)
                .thumbnailUrl(MAIN_IMG_THUMB)
                .representativeMenu(RPRSNTV_MENU)
                .startTime(usageTimes[0])
                .endTime(usageTimes[1])
                .build();
    }

    private LocalTime[] getUsageTime(String usageTimeStr) {
        LocalTime[] result = new LocalTime[2];
        String[] times = usageTimeStr.split("~");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);

        LocalTime startTime = LocalTime.parse(times[0].replace(".", "").toUpperCase(), formatter);
        LocalTime endTime = LocalTime.parse(times[1].replace(".", "").toUpperCase(), formatter);
        result[0] = startTime;
        result[1] = endTime;

        return result;
    }
}
