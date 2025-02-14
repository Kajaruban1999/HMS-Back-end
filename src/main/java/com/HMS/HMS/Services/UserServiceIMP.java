package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMP implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceIMP(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Users saveUser(Users users) {
        return userRepo.save(users);
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public Optional <Users> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<Users> findByPhoneNum(int phoneNum) {
        return userRepo.findByPhoneNum(phoneNum);
    }
}
