package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Roomsimages;
import com.HMS.HMS.Repositories.RoomImageRepo;
import com.HMS.HMS.Repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceIMP implements RoomService {

    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private RoomImageRepo roomImageRepo;

    @Override
    public Rooms saveRoom(Rooms rooms) {
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
        Optional<Rooms> room = roomRepo.findById(id);
        return room;
    }

    @Override
    public List<Rooms> GetAllRooms() {
        List<Rooms> rooms = roomRepo.findAll();
        return rooms;
    }

    @Override
    public void DeleteRoom(Long id) {
        roomRepo.deleteById(id);
    }

    public Optional<Rooms> updateRoom(Long id, Rooms updatedRoom) {
        return roomRepo.findById(id).map(existingRoom -> {
            existingRoom.setName(updatedRoom.getName());
            existingRoom.setCapacity(updatedRoom.getCapacity());
            existingRoom.setPrice(updatedRoom.getPrice());
            existingRoom.setDescription(updatedRoom.getDescription());

            if (updatedRoom.getImages() != null && !updatedRoom.getImages().isEmpty()) {
                roomImageRepo.deleteAll(existingRoom.getImages());
                for (Roomsimages image : updatedRoom.getImages()) {
                    image.setRoom(existingRoom);
                    roomImageRepo.save(image);
                }
                existingRoom.setImages(updatedRoom.getImages());
            }

            return roomRepo.save(existingRoom);
        });
    }
}