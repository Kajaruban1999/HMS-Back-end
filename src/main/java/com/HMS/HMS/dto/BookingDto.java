package com.HMS.HMS.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class BookingDto {
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int duration;
    private int participants;
    private Long userId;
    private Long roomId;
    private Long serviceId;
}
