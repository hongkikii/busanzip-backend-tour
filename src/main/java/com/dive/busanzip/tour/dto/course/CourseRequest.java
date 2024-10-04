package com.dive.busanzip.tour.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CourseRequest {
    // 식당 방문 횟수
    private Integer eatingCount;

    // 좋아하는 음식 타입
    // [KOREAN, CHINESE, JAPANESE, WESTERN, MEXICAN, ASIAN] 중 선택
    // 복수 선택 가능
    private String[] eatingTypes;

    // 좋아하는 음식(메뉴)
    // 복수 입력 가능
    private String likeMenus;

    // 음식 관련 요구사항
    // 음식 먹는 경우에만 (아닌 경우 빈 문자열)
    private String eatingRequirements;

    // 쇼핑 횟수
    private Integer shoppingCount;

    // 쇼핑 관련 요구사항
    // 쇼핑 하는 경우에만 (아닌 경우 빈 문자열)
    private String shoppingRequirements;

    // 명소 방문 횟수
    private Integer touristAttractionCount;

    // 명소 관련 요구사항
    // 명소 있는 경우에만 (아닌 경우 빈 문자열)
    private String touristAttractionRequirements;

    // 체험 횟수
    private Integer experienceCount;

    // 체험 관련 요구사항
    // 체험 있는 경우에만 (아닌 경우 빈 문자열)
    private String experienceRequirements;

    // 숙소 원하는 횟수
    private Integer accommodationCount;

    // 숙소 관련 요구사항
    // 숙소 요청하는 경우에만 (아닌 경우 빈 문자열)
    // [호텔, 모텔, 캠핑, 글램핑, 풀빌라, 펜션, 여관, 리조트, 게스트하우스, 여인숙] 중 복수 선택 가능
    // 선택 안 한 경우 빈 문자열
    private String[] accommodationKeywords;

    // 교통 수단
    // true -> 차 이용, false -> 대중교통 이용
    private Boolean isUsingCar;

    // 장소 간 최대 이동 시간
    private int maxMoveMin;

    // 사용자 원하는 순서
    // [RESTAURANT, SHOPPING, TOURIST_ATTRACTION, EXPERIENCE, ACCOMMODATION] 중 선택
    // 복수 선택 가능
    String[] sequence;

    /*
     * 좋아하는 음식: 메뉴(명사) 형태(ex. 봉골레 파스타)로 적길 권장
     * 요구사항: 인테리어, 음식, 장애 유무, 유아 동반 여부등 ... 자유롭게 작성!
     */
}
