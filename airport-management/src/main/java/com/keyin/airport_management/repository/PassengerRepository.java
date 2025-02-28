package com.keyin.airport_management.repository;

import com.keyin.airport_management.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}