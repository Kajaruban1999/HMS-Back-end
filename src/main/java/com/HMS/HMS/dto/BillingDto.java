package com.HMS.HMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingDto {
    private Long id;
    private int amount;
    private String paymentType;
    private String paymentDate;
    private Long bookingId;
}