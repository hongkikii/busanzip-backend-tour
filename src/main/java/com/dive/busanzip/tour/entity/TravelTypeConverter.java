package com.dive.busanzip.tour.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.EnumSet;

@Converter(autoApply = true)
public class TravelTypeConverter implements AttributeConverter<TravelType, String> {

    @Override
    public String convertToDatabaseColumn(TravelType travelType) {
        return travelType.getTravelTypeStr();
    }

    @Override
    public TravelType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(TravelType.class).stream()
                .filter(t -> t.getTravelTypeStr().equals(dbData))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
