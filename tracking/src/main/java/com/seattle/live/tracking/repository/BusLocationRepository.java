package com.seattle.live.tracking.repository;


import com.seattle.live.tracking.entity.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocation, Long> { }
