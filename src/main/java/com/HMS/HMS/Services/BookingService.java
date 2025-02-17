package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Booking;
import com.HMS.HMS.dto.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking saveBooking(BookingDto booking);
    Optional<Booking>getBookingById(Long id);
    List<Booking>getAllBooking();
    void deleteBooking(Long id);
}
