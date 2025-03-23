package com.HMS.HMS.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roomsimages {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @JsonProperty("pic")
    private String pic;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Rooms room;
}