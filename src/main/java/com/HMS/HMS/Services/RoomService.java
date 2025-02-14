package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Rooms;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Rooms saveRoom (Rooms rooms);
    Optional<Rooms>GetRoomsById(Long id);
    List<Rooms>GetAllRooms();
    void DeleteRoom(Long id);
}
