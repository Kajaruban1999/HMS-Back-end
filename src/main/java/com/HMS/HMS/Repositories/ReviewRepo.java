package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Reviews,Long> {
}
