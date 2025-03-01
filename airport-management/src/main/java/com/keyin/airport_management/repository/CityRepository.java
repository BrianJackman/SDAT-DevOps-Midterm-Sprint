package com.keyin.airport_management.repository;

import com.keyin.airport_management.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByNameAndState(String name, String state);
}