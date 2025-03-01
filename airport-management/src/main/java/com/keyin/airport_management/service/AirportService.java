package com.keyin.airport_management.service;

import com.keyin.airport_management.model.Airport;
import com.keyin.airport_management.model.Passenger;
import com.keyin.airport_management.repository.AirportRepository;
import com.keyin.airport_management.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public List<String> getAirportsInCities() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airport -> airport.getName() + " in " + airport.getCity().getName())
                .collect(Collectors.toList());
    }

    public List<String> getAircraftPassengersTravelledOn() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .flatMap(passenger -> {
                    if (passenger.getAircrafts() != null) {
                        return passenger.getAircrafts().stream();
                    } else {
                        return Stream.empty();
                    }
                })
                .map(aircraft -> aircraft.getName())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAirportsForAircraft() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airport -> airport.getName() + " can take off from and land at " + airport.getCity().getName())
                .collect(Collectors.toList());
    }

    public List<String> getAirportsUsedByPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .flatMap(passenger -> {
                    if (passenger.getAircrafts() != null) {
                        return passenger.getAircrafts().stream();
                    } else {
                        return Stream.empty();
                    }
                })
                .flatMap(aircraft -> {
                    if (aircraft.getAirports() != null) {
                        return aircraft.getAirports().stream();
                    } else {
                        return Stream.empty();
                    }
                })
                .map(airport -> airport.getName())
                .distinct()
                .collect(Collectors.toList());
    }
}