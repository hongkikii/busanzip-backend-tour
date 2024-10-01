package com.dive.busanzip.tour.repository;

import com.dive.busanzip.tour.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
