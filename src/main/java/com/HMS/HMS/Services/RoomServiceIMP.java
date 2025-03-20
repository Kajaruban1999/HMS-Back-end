package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Roomsimages;
import com.HMS.HMS.Repositories.RoomImageRepo;
import com.HMS.HMS.Repositories.RoomRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceIMP implements RoomService{

    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private RoomImageRepo roomImageRepo;

    @Override
    public Rooms saveRoom(Rooms rooms){
        Rooms savedRoom = roomRepo.save(rooms);

        if (rooms.getImages() != null && !rooms.getImages().isEmpty()) {
            for (Roomsimages image : rooms.getImages()) {
                image.setRoom(savedRoom);
                try {
                    roomImageRepo.save(image);
                } catch (Exception e) {
                    System.out.println("Error saving image: " + e.getMessage());
                }
            }
        }

        return savedRoom;

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
