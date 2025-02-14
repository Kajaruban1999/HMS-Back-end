package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private String paymentDate;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
