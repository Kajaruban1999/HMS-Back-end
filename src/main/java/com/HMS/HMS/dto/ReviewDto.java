package com.HMS.HMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long id;
    private String comments;
    private int stars;
    private Long roomId;
    private Long userId;
}
