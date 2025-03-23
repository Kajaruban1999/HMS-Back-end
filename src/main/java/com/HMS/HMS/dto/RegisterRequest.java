package com.HMS.HMS.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private String passportNic;
    private String country;
    private String password;
}
