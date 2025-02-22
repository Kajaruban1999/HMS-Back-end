package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
public class Billing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private String paymentType;
    private String paymentDate;


    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
