package com.dive.busanzip.tour.service;

import com.dive.busanzip.tour.dto.tour.Candidate;
import com.dive.busanzip.tour.dto.tour.CandidateInfo;
import com.dive.busanzip.tour.dto.tour.Course;
import com.dive.busanzip.tour.dto.tour.CourseInfo;
import com.dive.busanzip.tour.entity.TravelType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    // TODO : 모듈별 분리
    public Course getcourse(CourseInfo courseInfo) {
        /*
        1. 섹션 기준 장소 필터링
        */
        CandidateInfo[] restaurantCandidateInfos;
        // TODO : 네이밍 고민(코드 전체)
        CandidateInfo[] touristAttractionCandidateInfos;
        CandidateInfo[] shoppingCandidateInfos;
        CandidateInfo[] experienceCandidateInfos;
        if(courseInfo.getEatCount() > 0) {
            restaurantCandidateInfos = filterBy(courseInfo.getSection(), TravelType.RESTAURANT);
        }
        if(courseInfo.getTouristAttractionCount() > 0) {
            touristAttractionCandidateInfos = filterBy(courseInfo.getSection(), TravelType.TOURIST_ATTRACTION);
        }
        if(courseInfo.getTouristAttractionCount() > 0) {
            shoppingCandidateInfos = filterBy(courseInfo.getSection(), TravelType.SHOPPING);
        }
        if(courseInfo.getTouristAttractionCount() > 0) {
            experienceCandidateInfos = filterBy(courseInfo.getSection(), TravelType.EXPERIENCE);
        }

        /*
        2. 사용자 코멘트 부합 정도에 따른 장소 순서 선정
         */
        Candidate[] restaurantCandidate;
        Candidate[] touristAttractionCandidate;
        Candidate[] shoppingCandidate;
        Candidate[] experienceCandidate;

        // TODO : 사용자 맞춤형 장소 순서 선정 모델
        // RestTemplate...

        // getRestaurantSequenceByComment(restaurantCandidateInfos);
        // getTouristAttractionSequenceByComment(touristAttractionCandidateInfos);
        // getShoppingSequenceByComment(shoppingCandidateInfos);
        // getExperienceSequenceByComment(experienceCandidateInfos);

        /*
        3. 코스 타입 배치 알고리즘
         */
        TravelType[] sequence;
        if(courseInfo.getSequence().length > 0) {
            sequence = new TravelType[courseInfo.getSequence().length];
            for(int i = 0; i < courseInfo.getSequence().length; i++) {
                sequence[i] = TravelType.valueOf(courseInfo.getSequence()[i]); // ?
            }
        }
        else {
            sequence = planCourse(courseInfo);
        }


        /*
        4. 최종 장소 선정
         */



        /*
        5. 타입별 상세 정보 반환
         */

        return null;
    }

    private CandidateInfo[] filterBy(Integer[] section, TravelType travelType) {
        // 섹션, 타입에 해당하는 데이터 읽어서 반환

        return null;
    }


    // TODO : 코스 알고리즘 구체화
    private TravelType[] planCourse(CourseInfo courseInfo) {
            List<TravelType> activities = new ArrayList<>();

            // 명소 추가
            for (int i = 0; i < courseInfo.getTouristAttractionCount(); i++) {
                activities.add(TravelType.TOURIST_ATTRACTION);
            }

            // 쇼핑 추가
            for (int i = 0; i < courseInfo.getShoppingCount(); i++) {
                activities.add(TravelType.SHOPPING);
            }

            // 체험 추가
            for (int i = 0; i < courseInfo.getExperienceCount(); i++) {
                activities.add(TravelType.EXPERIENCE);
            }

            List<TravelType> result = new ArrayList<>();

            // 식사 횟수가 1회일 때
            if (courseInfo.getEatCount() == 1) {
                if (activities.size() == 1) {
                    // 체험만 1개 있을 경우 식사를 뒤에 배치
                    if (courseInfo.getExperienceCount() == 1) {
                        result.addAll(activities);
                        result.add(TravelType.RESTAURANT);
                    }
                    // 명소나 쇼핑만 1개 있을 경우 식사를 앞에 배치
                    else {
                        result.add(TravelType.RESTAURANT);
                        result.addAll(activities);
                    }
                } else if (activities.size() >= 2) {
                    // 활동이 2개 이상이면 중간에 식사 배치
                    int mid = activities.size() / 2;
                    result.addAll(activities.subList(0, mid));
                    result.add(TravelType.RESTAURANT);  // 중간에 식사 삽입
                    result.addAll(activities.subList(mid, activities.size()));
                }
            }
            // 식사가 여러 번일 때
            else if (courseInfo.getEatCount() > 1) {
                int activitiesSize = activities.size();
                int interval = activitiesSize / (courseInfo.getEatCount() - 1);  // 중간에 삽입할 간격 계산
                int activityIndex = 0;

                // 중간 중간 식사를 끼워 넣음
                for (int i = 0; i < courseInfo.getEatCount() - 1; i++) {
                    int end = Math.min(activityIndex + interval, activitiesSize);
                    result.addAll(activities.subList(activityIndex, end));
                    result.add(TravelType.RESTAURANT);  // 중간에 식사 추가
                    activityIndex = end;
                }

                // 남은 활동 추가
                result.addAll(activities.subList(activityIndex, activitiesSize));

                // 마지막에 식사 추가
                result.add(TravelType.RESTAURANT);
            }

            // 리스트를 배열로 변환하여 반환
            return result.toArray(new TravelType[0]);
    }
}
