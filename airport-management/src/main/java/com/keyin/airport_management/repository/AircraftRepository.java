package main.java.com.keyin.airport_management.repository;

import com.keyin.airport_management.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}