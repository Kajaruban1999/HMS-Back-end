package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Services.RoomServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<Rooms>createRooms(@RequestBody Rooms rooms){
        return ResponseEntity.ok((Rooms) roomService.saveRoom(rooms));
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
