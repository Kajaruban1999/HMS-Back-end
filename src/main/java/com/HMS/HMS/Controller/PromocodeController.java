package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Promocode;
import com.HMS.HMS.Entities.Userpromo;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.PromoCodeRepo;
import com.HMS.HMS.Repositories.UserPromoRepo;
import com.HMS.HMS.Repositories.UserRepo;
import com.HMS.HMS.Services.PromoCodeServiceIMP;
import com.HMS.HMS.dto.UserPromoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promo")
public class PromocodeController {

    private PromoCodeServiceIMP promoCodeService;

    @Autowired
    private UserRepo usersRepository;
    @Autowired
    private PromoCodeRepo promocodeRepository;
    @Autowired
    private UserPromoRepo userpromoRepository;

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

    @PostMapping("/promo-toggle/{userId}/promo")
    public ResponseEntity<Userpromo> allocatePromo(
            @PathVariable Long userId,
            @RequestBody UserPromoRequest request) {
        Optional<Users> userOpt = usersRepository.findById(userId);
        Optional<Promocode> promoOpt = promocodeRepository.findById(request.getPromoCodeId());

        if (userOpt.isEmpty() || promoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Users user = userOpt.get();
        Promocode promo = promoOpt.get();

        Userpromo userpromo = new Userpromo();
        userpromo.setUser(user);
        userpromo.setPromoCode(promo);
        userpromo.setFromDate(LocalDate.parse(request.getFromDate()));
        userpromo.setToDate(LocalDate.parse(request.getToDate()));
        userpromo.setStatus(request.getStatus());
        userpromo.setStatus(true);

        Userpromo savedUserpromo = userpromoRepository.save(userpromo);
        return ResponseEntity.ok(savedUserpromo);
    }

    @DeleteMapping("/promo-toggle/{userId}/promo/{userPromoId}")
    public ResponseEntity<Void> deallocatePromo(
            @PathVariable Long userId,
            @PathVariable Long userPromoId) {
        Optional<Userpromo> userpromoOpt = userpromoRepository.findById(userPromoId);

        if (userpromoOpt.isEmpty() || !userpromoOpt.get().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }

        userpromoRepository.deleteById(userPromoId);
        return ResponseEntity.noContent().build();
    }
}
