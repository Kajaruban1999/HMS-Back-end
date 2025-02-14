package com.HMS.HMS.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comments;
    private int stars;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
