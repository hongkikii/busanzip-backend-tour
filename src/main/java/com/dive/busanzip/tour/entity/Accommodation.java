package com.dive.busanzip.tour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Accommodation {

    @Id
    private Long id;
    private String name; // 업체명
    private String category; // 카테고리명
    private String district; // 시군구명
    private String town; // 읍면동명
    private Double longitude; // 경도
    private Double latitude; // 위도
    private String phoneNumber; // 전화번호
    private String website; // 홈페이지주소
    private Boolean hasParking; // 주차가능여부
    private Boolean hasRestroom; // 화장실유무
    private Boolean hasNursingRoom; // 수유실유무
    private Boolean hasLocker; // 물품보관함유무
    private Boolean hasBabyChair; // 유아거치대유무
    private Boolean isWheelchairAccessible; // 휠체어이동가능여부
    private Boolean hasBrailleBlock; // 점자유도로유무
    @Lob
    private String imageUrl;

    public static Accommodation create(
            Long id,
            String name,
            String category,
            String district,
            String town,
            double longitude,
            double latitude,
            String phoneNumber,
            String website,
            boolean hasParking,
            boolean hasRestroom,
            boolean hasNursingRoom,
            boolean hasLocker,
            boolean hasBabyChair,
            boolean isWheelchairAccessible,
            boolean hasBrailleBlock,
            String imageUrl
    ) {
        return Accommodation.builder()
                .id(id)
                .name(name)
                .category(category)
                .district(district)
                .town(town)
                .longitude(longitude)
                .latitude(latitude)
                .phoneNumber(phoneNumber)
                .website(website)
                .hasParking(hasParking)
                .hasRestroom(hasRestroom)
                .hasNursingRoom(hasNursingRoom)
                .hasLocker(hasLocker)
                .hasBabyChair(hasBabyChair)
                .isWheelchairAccessible(isWheelchairAccessible)
                .hasBrailleBlock(hasBrailleBlock)
                .imageUrl(imageUrl)
                .build();
    }
}
