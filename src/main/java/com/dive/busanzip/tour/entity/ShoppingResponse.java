package com.dive.busanzip.tour.entity;

import com.dive.busanzip.tour.dto.api.ShoppingData;
import lombok.Getter;

@Getter
public class ShoppingResponse {
    private ShoppingData[] item;
}
