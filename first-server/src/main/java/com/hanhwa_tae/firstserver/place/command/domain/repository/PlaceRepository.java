package com.hanhwa_tae.firstserver.place.command.domain.repository;

import com.hanhwa_tae.firstserver.place.command.domain.aggregate.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    boolean existsByPlaceId(int id);
}