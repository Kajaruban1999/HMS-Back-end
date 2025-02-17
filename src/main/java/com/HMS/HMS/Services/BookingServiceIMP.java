package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Booking;
import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Services;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.BookingRepo;
import com.HMS.HMS.Repositories.RoomRepo;
import com.HMS.HMS.Repositories.ServiceRepo;
import com.HMS.HMS.Repositories.UserRepo;
import com.HMS.HMS.dto.BookingDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Booking saveBooking(BookingDto bookingDto){
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking);
         Users users = userRepo.findById(bookingDto.getUserId()).orElseThrow(()-> new RuntimeException("User Not Found"));
        Rooms rooms =  roomRepo.findById(bookingDto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        Services  services = serviceRepo.findById(bookingDto.getServiceId()).orElseThrow(()-> new RuntimeException("Service not found"));

        booking.setService(services);
        booking.setRoom(rooms);
        booking.setUser(users);
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

}
