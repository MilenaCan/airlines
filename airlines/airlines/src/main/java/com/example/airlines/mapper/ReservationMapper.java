package com.example.airlines.mapper;

import com.example.airlines.dto.ReservationDto;
import com.example.airlines.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationMapper {

    public ReservationDto convertReservationToDto(Reservation reservation){
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setDateOfCreation(reservation.getDateOfCreation());
            reservationDto.setUsername(reservation.getUsername());
            reservationDto.setReservedSeats(reservation.getReservedSeats());
            return  reservationDto;
    }

    public Reservation covertDtoToReservation(ReservationDto reservationDto){
            Reservation reservation = new Reservation();
            reservation.setDateOfCreation(reservationDto.getDateOfCreation());
            reservation.setUsername(reservationDto.getUsername());
            reservation.setReservedSeats(reservationDto.getReservedSeats());
            return reservation;
    }

    public List<ReservationDto> reservationDtoList(List<Reservation>allReservations){
        List<ReservationDto> retVal = new ArrayList<>();
        for (var reservation : allReservations){
            var resesrvationDto =convertReservationToDto(reservation);
            retVal.add(resesrvationDto);
        }
        return retVal;
    }

    public List<Reservation> dtoReservationList(List<ReservationDto> allDtoReservations){
        List<Reservation> retVal = new ArrayList<>();
        for(var reservationDto : allDtoReservations){
            var reservation = covertDtoToReservation(reservationDto);
            retVal.add((reservation));
        }
        return  retVal;
    }
}
