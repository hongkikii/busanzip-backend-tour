package com.dive.busanzip.tour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Place {

    @Id @GeneratedValue
    private Long id;
    private Integer section;
    private String type;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    // TODO : 데이터 타입 결정
//    private ?? usageDay;
//    private ?? holiday;
//    private ?? usageTime;
    private String imageURL;
    private String usageAmount;
    private String contact;
    private String repMenu;

}
