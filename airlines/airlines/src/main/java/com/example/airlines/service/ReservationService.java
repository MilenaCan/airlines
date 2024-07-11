package com.example.airlines.service;

import com.example.airlines.dao.FligthDao;
import com.example.airlines.dao.ReservationDao;
import com.example.airlines.dto.ReservationDto;
import com.example.airlines.mapper.ReservationMapper;
import com.example.airlines.model.Flight;
import com.example.airlines.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservationService {



    private ReservationDao reservationDao;


    private ReservationMapper reservationMapper;


    private FligthDao flightDao;

    @Autowired
    public ReservationService(ReservationDao reservationDao, ReservationMapper reservationMapper, FligthDao flightDao) {
        this.reservationDao = reservationDao;
        this.reservationMapper = reservationMapper;
        this.flightDao = flightDao;
    }

    public List<ReservationDto> searchReservations(String username, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Reservation> foundReservation = reservationDao.findByUsername(username, pageable);
        //List<Reservation> retVal = foundReservation.getContent();
        return reservationMapper.reservationDtoList(foundReservation.getContent());
    }

    public Reservation createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setDateOfCreation(reservationDto.getDateOfCreation());
        reservation.setUsername(reservationDto.getUsername());
        reservation.setReservedSeats(reservationDto.getReservedSeats());


        Flight flight = flightDao.findById(reservationDto.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (flight.getAvailableSeats() < reservationDto.getReservedSeats()) {
            throw new RuntimeException("Not enough available seats");
        }
        flight.setAvailableSeats(flight.getAvailableSeats() - reservationDto.getReservedSeats());
        flightDao.save(flight);

        reservation.setFlight(flight);
        return reservationDao.save(reservation);

    }
}
