package com.dive.busanzip.tour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 자동 증가하는 기본 키

    private String name; // 업체명
    private String category; // 카테고리명
    private String uniqueNumber; // 필지고유번호
    private String city; // 시도명
    private String district; // 시군구명
    private String town; // 읍면동명
    private String village; // 리명
    private String streetNumber; // 번지
    private String legalDongCode; // 법정동코드
    private String adminDongCode; // 행정동코드
    private String roadNameCode; // 도로명코드
    private String roadName; // 도로명
    private String roadNameDetail; // 도로명상세
    private Double longitude; // 경도
    private Double latitude; // 위도
    private Boolean isClosed; // 폐업여부
    private String phoneNumber; // 전화번호
    private String website; // 홈페이지주소
    private Boolean hasParking; // 주차가능여부
    private Boolean hasRestroom; // 화장실유무
    private String restroomType; // 화장실타입
    private Boolean hasNursingRoom; // 수유실유무
    private Boolean hasLocker; // 물품보관함유무
    private Boolean hasBabyChair; // 유아거치대유무
    private Boolean isWheelchairAccessible; // 휠체어이동가능여부
    private Boolean hasBrailleBlock; // 점자유도로유무
    private LocalDate registrationDate; // 등록일자

    public static Accommodation create(
            String name,
            String category,
            String uniqueNumber,
            String city,
            String district,
            String town,
            String village,
            String streetNumber,
            String legalDongCode,
            String adminDongCode,
            String roadNameCode,
            String roadName,
            String roadNameDetail,
            double longitude,
            double latitude,
            boolean isClosed,
            String phoneNumber,
            String website,
            boolean hasParking,
            boolean hasRestroom,
            String restroomType,
            boolean hasNursingRoom,
            boolean hasLocker,
            boolean hasBabyChair,
            boolean isWheelchairAccessible,
            boolean hasBrailleBlock,
            LocalDate registrationDate
    ) {
        return Accommodation.builder()
                .name(name)
                .category(category)
                .uniqueNumber(uniqueNumber)
                .city(city)
                .district(district)
                .town(town)
                .village(village)
                .streetNumber(streetNumber)
                .legalDongCode(legalDongCode)
                .adminDongCode(adminDongCode)
                .roadNameCode(roadNameCode)
                .roadName(roadName)
                .roadNameDetail(roadNameDetail)
                .longitude(longitude)
                .latitude(latitude)
                .isClosed(isClosed)
                .phoneNumber(phoneNumber)
                .website(website)
                .hasParking(hasParking)
                .hasRestroom(hasRestroom)
                .restroomType(restroomType)
                .hasNursingRoom(hasNursingRoom)
                .hasLocker(hasLocker)
                .hasBabyChair(hasBabyChair)
                .isWheelchairAccessible(isWheelchairAccessible)
                .hasBrailleBlock(hasBrailleBlock)
                .registrationDate(registrationDate)
                .build();
    }
}
