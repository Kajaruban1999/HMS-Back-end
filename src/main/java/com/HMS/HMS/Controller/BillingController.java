package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Billing;
import com.HMS.HMS.Entities.Booking;
import com.HMS.HMS.Services.BillingServiceIMP;
import com.HMS.HMS.Services.BookingServiceIMP;
import com.HMS.HMS.dto.BillingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private BillingServiceIMP billingService;

    @Autowired
    public BillingController (BillingServiceIMP billingService){
        this.billingService=billingService;
    }
    @PostMapping("/create")
    public ResponseEntity<String>createBilling(@RequestBody BillingDto billing){

        try{
            Billing billing1 = billingService.saveBilling(billing);
            System.out.println(billing1);
        }
        catch (RuntimeException r){
            return ResponseEntity.ok((r.getMessage()));
        }
        return ResponseEntity.ok(("Saved billing"));
    }
}
