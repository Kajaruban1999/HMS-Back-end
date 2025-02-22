package com.HMS.HMS.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserpromoDto {
    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long promoCodeId;
    private Long userId;
}
