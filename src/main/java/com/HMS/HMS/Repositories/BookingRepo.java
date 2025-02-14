package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking,Long> {

}
