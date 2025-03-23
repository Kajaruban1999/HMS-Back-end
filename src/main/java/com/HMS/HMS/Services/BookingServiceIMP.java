package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Booking;
import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Services;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.*;
import com.HMS.HMS.dto.BookingDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceIMP implements BookingService{

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;



    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Override
    public Booking saveBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking);
        Users users = userRepo.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        Rooms rooms = null;
        if (bookingDto.getRoomId() != null) {
            rooms = roomRepo.findById(bookingDto.getRoomId()).orElse(null);
        }
        Services services = null;
        if (bookingDto.getServiceId() != null) {
            services = serviceRepo.findById(bookingDto.getServiceId()).orElse(null);
        }
        booking.setUser(users);
        booking.setRoom(rooms);
        booking.setService(services);
        booking.setStatus("PENDING");
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking>getAllBooking(){
        return bookingRepo.findAll();
    }
    @Override
    public Optional<Booking>getBookingById(Long id){
        return bookingRepo.findById(id);
    }
    @Override
    public void deleteBooking(Long id){
        bookingRepo.deleteById(id);
    }
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public Optional<List<Booking>> getBookingsByUserId(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    @Transactional
    public void deleteBookingById(Long id) {
        if (!bookingRepo.existsById(id)) {
            throw new RuntimeException("Booking not found with ID: " + id);
        }
        bookingRepo.deleteById(id);
    }
    @Transactional
    public Booking updateBookingStatus(Long id, String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

//        List<String> validStatuses = Arrays.asList("PENDING", "CONFIRMED", "CANCELLED", "COMPLETED");
//        if (!validStatuses.contains(newStatus.toUpperCase())) {
//            throw new IllegalArgumentException("Invalid status: " + newStatus + ". Allowed values are: " + validStatuses);
//        }
        String trimmedStatus = newStatus.trim().toUpperCase().replace("\"", "");
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));

        booking.setStatus(trimmedStatus);
        return bookingRepo.save(booking);
    }



}
