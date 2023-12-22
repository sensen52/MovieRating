package com.example.demo.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewDTO {
	
	private int reviewId;
	private int movieId;
	private int userId;
	private String movieName;
	private String reviewRegisterDate;
	private String reviewUpdateDate;
	private String reviewContent;
	private String reviewRate;
	private int reviewLike;

	

}
