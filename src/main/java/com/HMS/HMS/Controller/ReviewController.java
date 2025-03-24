package com.HMS.HMS.Controller;

import com.HMS.HMS.Services.ReviewServiceIMP;
import com.HMS.HMS.dto.ReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Review")
public class ReviewController {

    private ReviewServiceIMP reviewService;

    public ReviewController (ReviewServiceIMP reviewService){

        this.reviewService=reviewService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto review){
        try {
            reviewService.saveReview(review);
        }
        catch (RuntimeException r){
            System.out.println(r.getMessage());
            return ResponseEntity.ok((r.getMessage()));
        }

        return ResponseEntity.ok(("saved Review"));
    }

    @GetMapping
    public ResponseEntity<?> getAllReview(){
        return ResponseEntity.ok(reviewService.getAllReview());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }


}
