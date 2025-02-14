package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepo extends JpaRepository<Rooms,Long> {
    Optional<Rooms> findById(Long aLong);
    Optional<Rooms>findByName(String name);
}
