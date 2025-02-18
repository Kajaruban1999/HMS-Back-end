package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Promocode;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.PromoCodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromoCodeServiceIMP implements PromoCodeService{

    @Autowired
    private PromoCodeRepo promoCodeRepo;

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
}
