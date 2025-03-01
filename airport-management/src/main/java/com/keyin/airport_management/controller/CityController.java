package com.keyin.airport_management.controller;

import com.keyin.airport_management.model.City;
import com.keyin.airport_management.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<List<City>> createCities(@RequestBody List<City> cities) {
        try {
            List<City> savedCities = cityRepository.saveAll(cities);
            return new ResponseEntity<>(savedCities, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCities() {
        try {
            cityRepository.deleteAll();
            return new ResponseEntity<>("All cities deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete cities: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}