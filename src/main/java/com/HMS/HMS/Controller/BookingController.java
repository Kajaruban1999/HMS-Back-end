package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Booking;
import com.HMS.HMS.Services.BookingServiceIMP;
import com.HMS.HMS.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/booking")
public class BookingController {
    private BookingServiceIMP bookingService;

    @Autowired
    public BookingController (BookingServiceIMP bookingService){
        this.bookingService=bookingService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingDto booking){
        try{

           Booking booking1 = bookingService.saveBooking(booking);
            System.out.println(booking1);
        }
        catch (RuntimeException r){
            return ResponseEntity.ok((r.getMessage()+ "Something went wrong"));
        }
        return ResponseEntity.ok(("Ok"));
    }
}
