package com.dive.busanzip.tour.repository;

import com.dive.busanzip.tour.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
