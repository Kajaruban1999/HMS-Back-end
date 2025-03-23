package com.HMS.HMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPromoRequest {

    private Long promoCodeId;
    private String fromDate;
    private String toDate;
    private Boolean status;
}
