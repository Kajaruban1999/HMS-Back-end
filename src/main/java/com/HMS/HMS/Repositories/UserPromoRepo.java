package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Userpromo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPromoRepo extends JpaRepository <Userpromo,Long> {
    @Query("SELECT up FROM Userpromo up WHERE up.user.id = :userId AND up.promoCode.code = :promoCodeId")
    List<Userpromo> findByUserIdAndPromoCodeId(Long userId, String promoCodeId);
}
