package com.example.airlines.mapper;

import com.example.airlines.dto.FlightDto;
import com.example.airlines.model.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightMapper {
    public FlightDto converFlightToDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setAvailableSeats(flight.getAvailableSeats());
        flightDto.setFlightDate(flight.getFlightDate());
        flightDto.setDestination(flight.getDestination());

        return flightDto;
    }

    public Flight convertDtoToFlight(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setAvailableSeats(flightDto.getAvailableSeats());
        flight.setFlightDate(flightDto.getFlightDate());
        flight.setDestination(flightDto.getDestination());

        return flight;
    }

    public List<FlightDto> flightDtoList(List<Flight> allFlights) {
        List<FlightDto> retVal = new ArrayList<>();
        for (var flight : allFlights) {
            var flightDto = converFlightToDto(flight);
            retVal.add(flightDto);
        }
        return retVal;
    }

    public List<Flight> dtoFlightList(List<FlightDto> allDtoFlights) {
        List<Flight> retVal = new ArrayList<>();
        for (var flightDto : allDtoFlights) {
            var flight = convertDtoToFlight(flightDto);
            retVal.add(flight);
        }
        return retVal;
    }

}
