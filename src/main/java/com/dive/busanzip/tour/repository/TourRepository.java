package com.dive.busanzip.tour.repository;

import com.dive.busanzip.tour.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Place, Long> {
}
