package com.dive.busanzip.tour.dto.course;

import com.dive.busanzip.tour.entity.Accommodation;
import com.dive.busanzip.tour.entity.Experience;
import com.dive.busanzip.tour.entity.Restaurant;
import com.dive.busanzip.tour.entity.Shopping;
import com.dive.busanzip.tour.entity.TouristAttraction;
import com.dive.busanzip.tour.entity.TravelType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Place {

    private String type;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private String usageDay; // X(NULL): 맛집, 숙박
    private String holiday; // X(NULL): 맛집, 숙박
    private String usageTime; // X(NULL): 숙박
    private String imageURL; // X(NULL): 숙박
    private String usagePrice; // X(NULL): 맛집, 숙박
    private String contact;
    private String repMenu; // X(NULL): 쇼핑, 명소, 체험, 숙박

    // TODO : 편의시설 정보 추가

    public static Place from(Restaurant restaurant) {
        return Place.builder()
                .type(TravelType.RESTAURANT.getTravelTypeStr())
                .name(restaurant.getName())
                .description(restaurant.getDetails())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .usageTime(restaurant.getUsageTime())
                .imageURL(restaurant.getThumbnailUrl())
                .contact(restaurant.getPhone())
                .repMenu(restaurant.getRepresentativeMenu())
                .build();
    }

    public static Place from(Shopping shopping) {
        return Place.builder()
                .type(TravelType.SHOPPING.getTravelTypeStr())
                .name(shopping.getName())
                .description(shopping.getDetails())
                .latitude(shopping.getLatitude())
                .longitude(shopping.getLongitude())
                .usageDay(shopping.getUsageDay())
                .holiday(shopping.getHolidayInfo())
                .usageTime(shopping.getUsageTime())
                .imageURL(shopping.getThumbnailUrl())
                .usagePrice(shopping.getUsagePrice())
                .contact(shopping.getPhone())
                .build();
    }

    public static Place from(TouristAttraction touristAttraction) {
        return Place.builder()
                .type(TravelType.TOURIST_ATTRACTION.getTravelTypeStr())
                .name(touristAttraction.getName())
                .description(touristAttraction.getDetails())
                .latitude(touristAttraction.getLatitude())
                .longitude(touristAttraction.getLongitude())
                .usageDay(touristAttraction.getUsageDay())
                .holiday(touristAttraction.getHolidayInfo())
                .usageTime(touristAttraction.getUsageTime())
                .imageURL(touristAttraction.getThumbnailUrl())
                .usagePrice(touristAttraction.getUsagePrice())
                .contact(touristAttraction.getPhone())
                .build();
    }

    public static Place from(Experience experience) {
        return Place.builder()
                .type(TravelType.EXPERIENCE.getTravelTypeStr())
                .name(experience.getName())
                .description(experience.getDetails())
                .latitude(experience.getLatitude())
                .longitude(experience.getLongitude())
                .usageDay(experience.getUsageDay())
                .holiday(experience.getHolidayInfo())
                .usageTime(experience.getUsageTime())
                .imageURL(experience.getThumbnailUrl())
                .usagePrice(experience.getUsagePrice())
                .contact(experience.getPhone())
                .build();
    }

    public static Place from(Accommodation accommodation) {
        return Place.builder()
                .type(TravelType.ACCOMMODATION.getTravelTypeStr())
                .name(accommodation.getName())
                .latitude(accommodation.getLatitude())
                .longitude(accommodation.getLongitude())
                .contact(accommodation.getPhoneNumber())
                .build();
    }
}
