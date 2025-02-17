package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepo extends JpaRepository<Services,Long> {
//    Optional<Services>  findById(Long serviceid);
}
