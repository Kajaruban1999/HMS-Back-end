package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Roomsimages;
import com.HMS.HMS.Services.RoomServiceIMP;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomServiceIMP roomService;

    @Autowired
    public RoomController (RoomServiceIMP roomService){
        this.roomService = roomService;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    @Transactional
    public ResponseEntity<String> createRooms(
            @RequestParam("name") String name,
            @RequestParam("capacity") int capacity,
            @RequestParam("price") int price,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Rooms room = new Rooms();
            room.setName(name);
            room.setCapacity(capacity);
            room.setPrice(price);
            room.setDescription(description);
            List<Roomsimages> images = new ArrayList<>();
            if (image != null && !image.isEmpty()) {
                Roomsimages roomImage = new Roomsimages();
                roomImage.setPic(image.getBytes());
                roomImage.setRoom(room);
                images.add(roomImage);
            }
            room.setImages(images);

            roomService.saveRoom(room);
            return ResponseEntity.ok("Room created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating room: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rooms>>getRoomById(@PathVariable Long id){
        return ResponseEntity.ok(roomService.GetRoomsById(id));
    }

    @GetMapping
    public ResponseEntity<List<Rooms>>getAllrooms(){
        return ResponseEntity.ok(roomService.GetAllRooms());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String>deleteRooms(@PathVariable Long id){
        roomService.DeleteRoom(id);
        return ResponseEntity.ok("Delete successfully");
    }
}
