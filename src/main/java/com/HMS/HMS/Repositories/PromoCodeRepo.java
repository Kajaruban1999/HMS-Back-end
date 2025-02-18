package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepo extends JpaRepository<Promocode,Long> {
}
