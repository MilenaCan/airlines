package com.example.airlines.dao;

import com.example.airlines.dto.ReservationDto;
import com.example.airlines.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservations WHERE reservations.user_name  LIKE :username%", nativeQuery = true)
    Page<Reservation> findByUsername(@Param("username") String username, Pageable pageable);

}
