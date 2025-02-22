package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userpromo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fromDate;
    private LocalDate toDate;

    @ManyToOne
    @JoinColumn(name = "promoCode_id")
    private Promocode promoCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


}
