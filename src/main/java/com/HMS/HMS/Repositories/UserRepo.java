package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPhoneNum(int phoneNum);
}
