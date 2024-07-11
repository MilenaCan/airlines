package com.example.airlines.controller;

import com.example.airlines.dao.FligthDao;
import com.example.airlines.dto.FlightDto;
import com.example.airlines.mapper.FlightMapper;
import com.example.airlines.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private FligthDao fligthDao;

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights(){
        List<Flight> flights = fligthDao.findAll();
        List<FlightDto> flightDtos = flightMapper.flightDtoList(flights);
        return ResponseEntity.ok(flightDtos);
    }
}
