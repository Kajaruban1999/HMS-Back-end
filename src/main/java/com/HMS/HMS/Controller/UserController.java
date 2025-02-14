package com.HMS.HMS.Controller;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Services.UserServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceIMP userService;

    @Autowired
    public UserController(UserServiceIMP userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        return ResponseEntity.ok((Users) userService.saveUser(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Users>> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/phone/{phoneNum}")
    public ResponseEntity<Optional<Users>> getUserByPhone(@PathVariable int phoneNum) {
        return ResponseEntity.ok(userService.findByPhoneNum(phoneNum));
    }
}
