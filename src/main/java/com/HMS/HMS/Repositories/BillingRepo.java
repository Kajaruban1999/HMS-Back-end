package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingRepo extends JpaRepository<Billing,Long> {
    @Query("SELECT b FROM Billing b WHERE b.booking.id = :bookingId")
    Optional<Billing> findByBookingId(Long bookingId);

}
