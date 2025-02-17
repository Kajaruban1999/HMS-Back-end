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

        // Save images if they exist
        if (rooms.getImages() != null) {
            for (Roomsimages image : rooms.getImages()) {
                image.setPic(Base64.decodeBase64(new String(image.getPic())));
                image.setRoom(savedRoom);
                roomImageRepo.save(image);

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
