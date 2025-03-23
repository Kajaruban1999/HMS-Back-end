package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Booking;
import com.HMS.HMS.Entities.Promocode;
import com.HMS.HMS.Entities.Userpromo;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.BookingRepo;
import com.HMS.HMS.Repositories.UserRepo;
import com.HMS.HMS.Services.BookingServiceIMP;
import com.HMS.HMS.Services.PromoCodeServiceIMP;
import com.HMS.HMS.dto.BookingDto;
import com.HMS.HMS.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private BookingServiceIMP bookingService;

    @Autowired
    public BookingController (BookingServiceIMP bookingService){
        this.bookingService=bookingService;
    }
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private PromoCodeServiceIMP promoCodeService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createBooking(@RequestBody BookingDto booking) {
        try {
            Booking savedBooking = bookingService.saveBooking(booking);
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedBooking.getId());
            response.put("message", "saved Booking");
            return ResponseEntity.ok(response);
        } catch (RuntimeException r) {
            System.out.println(r.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", r.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/my-booking")
    public ResponseEntity<?> getMyBookingDetails(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String email = jwtUtil.extractUsername(jwt);

            Optional<Users> user = userRepository.findByEmail(email);
            Optional<List <Booking>> booking = bookingRepo.findByUserId(user.get().getId());

            return ResponseEntity.ok(booking.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Booking> updateBookingStatus(@PathVariable Long id, @RequestBody String newStatus) {
        Booking updatedBooking = bookingService.updateBookingStatus(id, newStatus);
        return ResponseEntity.ok(updatedBooking);
    }
    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> checkUserPromoAssociation(
            @RequestParam Long userId,
            @RequestParam String promoCodeId) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("promoCodeId", promoCodeId);

        boolean isAssociated = promoCodeService.isUserAssociatedWithPromo(userId, promoCodeId);
        response.put("isAssociated", isAssociated);

        if (isAssociated) {
            Userpromo userPromo = promoCodeService.getValidUserPromo(userId, promoCodeId);
            if (userPromo != null) {
                Promocode promo = userPromo.getPromoCode();
                response.put("discountAmount", promo.getDiscount());
                response.put("discountDescription", promo.getDescription());
                response.put("message", "User is associated with this promo code and it has been applied");
            } else {
                response.put("message", "No valid promo code available (already used or expired)");
                response.put("isAssociated", false);
            }
        } else {
            response.put("message", "No association found or promo code is invalid");
        }
        return ResponseEntity.ok(response);
    }
}
