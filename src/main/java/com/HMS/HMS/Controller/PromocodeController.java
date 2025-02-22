package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Promocode;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Services.PromoCodeServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promo")
public class PromocodeController {

    private PromoCodeServiceIMP promoCodeService;

    @Autowired
    public PromocodeController(PromoCodeServiceIMP promoCodeService){
        this.promoCodeService=promoCodeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Promocode> createPromo(@RequestBody Promocode promocode) {
        Promocode savedPromo = promoCodeService.savePromo(promocode);
        return ResponseEntity.ok((Promocode) promoCodeService.savePromo(promocode));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Promocode>> getPromoById(@PathVariable Long id) {
        return ResponseEntity.ok(promoCodeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Promocode>> getAllPromo() {
        return ResponseEntity.ok(promoCodeService.getAllPromo());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromo(@PathVariable Long id) {
        promoCodeService.DeletePromo(id);
        return ResponseEntity.noContent().build();
    }
}
