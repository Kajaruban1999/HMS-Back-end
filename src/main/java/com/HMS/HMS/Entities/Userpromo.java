package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userpromo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "promo_code_id")
    private Promocode promoCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String fromDate;
    private String toDate;
}
