package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Reviews,Long> {
}
