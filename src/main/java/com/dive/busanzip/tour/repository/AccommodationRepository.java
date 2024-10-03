package com.dive.busanzip.tour.repository;

import com.dive.busanzip.tour.entity.Accommodation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    @Query(value = "SELECT *, " +
            "( 6371 * ACOS( " +
            "COS(RADIANS(:latitude)) * COS(RADIANS(a.latitude)) * " +
            "COS(RADIANS(a.longitude) - RADIANS(:longitude)) + " +
            "SIN(RADIANS(:latitude)) * SIN(RADIANS(a.latitude)) " +
            ")) AS distance " +
            "FROM accommodation a " +
            "WHERE " +
            "(a.name LIKE %:keyword%) " +
            "ORDER BY distance " +
            "LIMIT 1", nativeQuery = true)
    Optional<Accommodation> findNearestByKeyword(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("keyword") String keyword
    );

    @Query("SELECT a FROM Accommodation a WHERE a.name LIKE %:keyword%")
    List<Accommodation> findByKeyword(@Param("keyword") String keyword);
}
