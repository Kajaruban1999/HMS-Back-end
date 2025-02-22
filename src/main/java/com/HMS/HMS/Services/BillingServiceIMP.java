package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Billing;
import com.HMS.HMS.Entities.Booking;
import com.HMS.HMS.Repositories.BillingRepo;
import com.HMS.HMS.Repositories.BookingRepo;
import com.HMS.HMS.dto.BillingDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceIMP implements BillingService{
    @Autowired
    private BillingRepo billingRepo;

    @Autowired
    private BookingRepo bookingRepo;

    public Billing saveBilling(BillingDto billingDto){
        Billing billing = new Billing();
        BeanUtils.copyProperties(billingDto,billing);
        Booking booking = bookingRepo.findById(billingDto.getBookingId()).orElseThrow(()->new RuntimeException("Booking Not found"));

        billing.setBooking(booking);
        return billingRepo.save(billing);
    }
}
