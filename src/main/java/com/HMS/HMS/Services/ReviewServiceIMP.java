package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Reviews;
import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.ReviewRepo;
import com.HMS.HMS.Repositories.RoomRepo;
import com.HMS.HMS.Repositories.UserRepo;
import com.HMS.HMS.dto.ReviewDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceIMP implements ReviewService{

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Reviews saveReview(ReviewDto reviewDto){
        Reviews reviews = new Reviews();
        BeanUtils.copyProperties(reviewDto, reviews);
        Rooms rooms =  roomRepo.findById(reviewDto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found "));
        Users users = userRepo.findById(reviewDto.getUserId()).orElseThrow(()-> new RuntimeException("User Not Found "));

        reviews.setRoom(rooms);
        reviews.setUser(users);
        return reviewRepo.save(reviews);
    }
    @Override
    public List<Reviews> getAllReview(){
        return reviewRepo.findAll();
    }
    @Override
    public Optional<Reviews> getReviewById(Long id){
        return reviewRepo.findById(id);
    }
    @Override
    public void deleteReview(Long id){
        reviewRepo.deleteById(id);
    }
}
