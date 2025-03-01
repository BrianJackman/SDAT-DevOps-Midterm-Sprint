package com.keyin.airport_management.controller;

import com.keyin.airport_management.model.Aircraft;
import com.keyin.airport_management.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    @Autowired
    private AircraftRepository aircraftRepository;

    @GetMapping
    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<List<Aircraft>> createAircraft(@RequestBody List<Aircraft> aircraft) {
        try {
            List<Aircraft> savedAircraft = aircraftRepository.saveAll(aircraft);
            return new ResponseEntity<>(savedAircraft, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllAircraft() {
        try {
            aircraftRepository.deleteAll();
            return new ResponseEntity<>("All aircraft deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete aircraft: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}