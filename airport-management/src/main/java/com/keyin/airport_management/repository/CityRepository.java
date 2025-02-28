package com.keyin.airport_management.repository;

import com.keyin.airport_management.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}