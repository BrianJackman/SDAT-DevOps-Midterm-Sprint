package main.java.com.keyin.airport_management.repository;

import com.keyin.airport_management.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}