package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Users;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Users saveUser(Users users);
    Optional<Users> getUserById(Long id);
    List<Users> getAllUsers();
    void deleteUser(Long id);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPhoneNum(int phoneNum);
}
