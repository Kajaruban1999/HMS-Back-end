package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Promocode;
import com.HMS.HMS.Entities.Userpromo;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.PromoCodeRepo;
import com.HMS.HMS.Repositories.UserPromoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromoCodeServiceIMP implements PromoCodeService{

    @Autowired
    private PromoCodeRepo promoCodeRepo;

    @Autowired
    private UserPromoRepo userPromoRepo;

    @Override
    public Promocode savePromo(Promocode promocode) {
        return promoCodeRepo.save(promocode);
    }
    @Override
    public Optional<Promocode> getById(Long id) {
        return promoCodeRepo.findById(id);
    }

    @Override
    public List<Promocode> getAllPromo() {
        return promoCodeRepo.findAll();
    }
    @Override
    public void DeletePromo(Long id) {
        promoCodeRepo.deleteById(id);
    }

    public boolean isUserAssociatedWithPromo(Long userId, String promoCodeId) {
        List<Userpromo> userPromos = userPromoRepo.findByUserIdAndPromoCodeId(userId, promoCodeId);
        return userPromos.stream().anyMatch(Userpromo::getStatus);
    }

    public Userpromo getValidUserPromo(Long userId, String promoCodeId) {
        List<Userpromo> userPromos = userPromoRepo.findByUserIdAndPromoCodeId(userId, promoCodeId);
        if (userPromos.isEmpty()) {
            return null;
        }
        Optional<Userpromo> validPromo = userPromos.stream()
                .filter(Userpromo::getStatus)
                .findFirst();

        if (validPromo.isPresent()) {
            Userpromo promo = validPromo.get();
            promo.setStatus(false);
            userPromoRepo.save(promo);
            return promo;
        }
        return null;
    }
}
