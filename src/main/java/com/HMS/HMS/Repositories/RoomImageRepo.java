package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Roomsimages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomImageRepo extends JpaRepository <Roomsimages,Long> {
    List<Roomsimages> findByRoomId(Integer room_id);
}
