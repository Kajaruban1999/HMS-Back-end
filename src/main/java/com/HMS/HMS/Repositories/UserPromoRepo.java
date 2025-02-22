package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Userpromo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPromoRepo extends JpaRepository <Userpromo,Long> {
}
