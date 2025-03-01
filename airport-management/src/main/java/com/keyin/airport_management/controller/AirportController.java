package com.keyin.airport_management.controller;

import com.keyin.airport_management.model.Airport;
import com.keyin.airport_management.model.City;
import com.keyin.airport_management.model.Passenger;
import com.keyin.airport_management.repository.AirportRepository;
import com.keyin.airport_management.repository.CityRepository;
import com.keyin.airport_management.repository.PassengerRepository;
import com.keyin.airport_management.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AirportController {

    private static final Logger LOGGER = Logger.getLogger(AirportController.class.getName());

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @PostMapping("/airports")
    public ResponseEntity<List<Airport>> createAirports(@RequestBody List<Airport> airports) {
        try {
            for (Airport airport : airports) {
                City city = cityRepository.findByNameAndState(airport.getCity().getName(), airport.getCity().getState());
                if (city == null) {
                    city = new City(airport.getCity().getName(), airport.getCity().getState(), airport.getCity().getPopulation());
                    city = cityRepository.save(city);
                }
                airport.setCity(city);
            }
            List<Airport> savedAirports = airportRepository.saveAll(airports);
            return new ResponseEntity<>(savedAirports, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create airports", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/airports")
    public ResponseEntity<String> deleteAllAirports() {
        try {
            airportRepository.deleteAll();
            return new ResponseEntity<>("All airports deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete airports", e);
            return new ResponseEntity<>("Failed to delete airports: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/airports-in-cities")
    public ResponseEntity<List<String>> getAirportsInCities() {
        try {
            List<String> result = airportService.getAirportsInCities();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch airports in cities", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/aircraft-passengers-travelled-on")
    public ResponseEntity<List<String>> getAircraftPassengersTravelledOn() {
        try {
            List<String> result = airportService.getAircraftPassengersTravelledOn();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch aircraft passengers travelled on", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/airports-for-aircraft")
    public ResponseEntity<List<String>> getAirportsForAircraft() {
        try {
            List<String> result = airportService.getAirportsForAircraft();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch airports for aircraft", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/airports-used-by-passengers")
    public ResponseEntity<List<String>> getAirportsUsedByPassengers() {
        try {
            List<String> result = airportService.getAirportsUsedByPassengers();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch airports used by passengers", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}