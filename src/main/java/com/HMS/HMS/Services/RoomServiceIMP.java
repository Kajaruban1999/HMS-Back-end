package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceIMP implements RoomService{

    private final RoomRepo roomRepo;

    @Autowired
    public RoomServiceIMP (RoomRepo roomRepo){
       this.roomRepo = roomRepo;
    }
    @Override
    public Rooms saveRoom(Rooms rooms){
        return roomRepo.save(rooms);
    }

    @Override
    public Optional<Rooms> GetRoomsById(Long id) {
        return roomRepo.findById(id);
    }

    @Override
    public List<Rooms> GetAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public void DeleteRoom(Long id) {
          roomRepo.deleteById(id);
    }
}
