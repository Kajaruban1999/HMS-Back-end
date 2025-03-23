package com.HMS.HMS.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private int duration;
    private int participants;
    private String status;
    @Version
    private Long version;

  //  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "room_id")
    private Rooms room;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "service_id")
    private Services service;

//    @JsonIgnore
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Billing> billings = new ArrayList<>();

}
