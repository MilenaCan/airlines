package com.example.airlines.controller;

import com.example.airlines.dto.ReservationDto;
import com.example.airlines.exception.InvalidReservationDataException;
import com.example.airlines.exception.ReservationNotFoundException;
import com.example.airlines.model.Reservation;
import com.example.airlines.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto reservationDto) {
        if (reservationDto == null || reservationDto.getFlightId() == null || reservationDto.getUsername() == null || reservationDto.getReservedSeats() == null || reservationDto.getUsername().trim().equals("") ) {
            throw new InvalidReservationDataException("Invalid reservation data");
        }

        try {
            Reservation reservation = reservationService.createReservation(reservationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
        } catch (Exception e) {
            throw new InvalidReservationDataException("Failed to create reservation: " + e.getMessage());
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<ReservationDto>> searchReservations(@RequestParam String username, @RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize){
        try {
            List<ReservationDto> reservations = reservationService.searchReservations(username, pageNumber, pageSize);
            if (reservations.isEmpty()) {
                throw new ReservationNotFoundException("No reservations found for username: " + username);
            }
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            throw new ReservationNotFoundException("Error occurred while searching reservations: " + e.getMessage());
        }
    }

}
