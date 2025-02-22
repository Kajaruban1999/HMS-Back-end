package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Userpromo;
import com.HMS.HMS.Services.UserPromoServiceIMP;
import com.HMS.HMS.dto.UserpromoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userpromo")
public class UserpromoController {
    private UserPromoServiceIMP userPromoService;

    @Autowired
    public UserpromoController (UserPromoServiceIMP userPromoService){
        this.userPromoService=userPromoService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> CreateUserPromo(@RequestBody UserpromoDto userpromo){
        try{
            Userpromo userPromo1 = userPromoService.saveUserpromo(userpromo);
            System.out.println(userPromo1);
        }
        catch (RuntimeException r){
            return ResponseEntity.ok((r.getMessage()));
        }
        return ResponseEntity.ok(("Set UserPromoCode"));
    }
}
