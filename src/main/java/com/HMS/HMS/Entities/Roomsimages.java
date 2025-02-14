package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roomsimages {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pic;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms room;
}
