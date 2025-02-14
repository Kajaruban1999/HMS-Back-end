package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int capacity;
    private int price;
    private String description;

    @OneToMany(mappedBy = "room")
    private List<Roomsimages> images;
}
