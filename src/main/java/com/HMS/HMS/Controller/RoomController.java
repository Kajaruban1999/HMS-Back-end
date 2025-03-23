package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Roomsimages;
import com.HMS.HMS.Services.RoomServiceIMP;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomServiceIMP roomService;

    @Autowired
    public RoomController(RoomServiceIMP roomService) {
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
                // Convert image bytes to Base64 string
                String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
                roomImage.setPic(base64Image);
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
    public ResponseEntity<Optional<Rooms>> getRoomById(@PathVariable Long id) {
        Optional<Rooms> room = roomService.GetRoomsById(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping
    public ResponseEntity<List<Rooms>> getAllrooms() {
        List<Rooms> rooms = roomService.GetAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRooms(@PathVariable Long id) {
        roomService.DeleteRoom(id);
        return ResponseEntity.ok("Delete successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Rooms> updateRoom(@PathVariable Long id, @RequestBody Rooms updatedRoom) {
        return roomService.updateRoom(id, updatedRoom)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}