package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Long> {
    Optional<Booking>findById(Long aLong);
}
