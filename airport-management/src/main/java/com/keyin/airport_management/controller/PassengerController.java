package com.keyin.airport_management.controller;

import com.keyin.airport_management.model.Passenger;
import com.keyin.airport_management.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<List<Passenger>> createPassengers(@RequestBody List<Passenger> passengers) {
        try {
            List<Passenger> savedPassengers = passengerRepository.saveAll(passengers);
            return new ResponseEntity<>(savedPassengers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPassengers() {
        try {
            passengerRepository.deleteAll();
            return new ResponseEntity<>("All passengers deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete passengers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}