package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {

}
