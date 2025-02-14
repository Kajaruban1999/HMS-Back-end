package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Serviceimages {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pic;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;
}
