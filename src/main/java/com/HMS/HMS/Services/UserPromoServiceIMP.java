package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Promocode;
import com.HMS.HMS.Entities.Userpromo;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.PromoCodeRepo;
import com.HMS.HMS.Repositories.UserPromoRepo;
import com.HMS.HMS.Repositories.UserRepo;
import com.HMS.HMS.dto.UserpromoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPromoServiceIMP implements UserPromoService{

    @Autowired
    private UserPromoRepo userPromoRepo;

    @Autowired
    private PromoCodeRepo promoCodeRepo;

    @Autowired
    private UserRepo userRepo;

    public Userpromo saveUserpromo(UserpromoDto userpromoDto){
        Userpromo userpromo = new Userpromo();
        BeanUtils.copyProperties(userpromoDto,userpromo);
        Promocode promocode = promoCodeRepo.findById(userpromoDto.getPromoCodeId()).orElseThrow(()->new RuntimeException("PromoCode not found"));
        Users users = userRepo.findById(userpromoDto.getUserId()).orElseThrow(()->new RuntimeException("User not found"));

        userpromo.setPromoCode(promocode);
        userpromo.setUser(users);
        return userPromoRepo.save(userpromo);
    }
}
