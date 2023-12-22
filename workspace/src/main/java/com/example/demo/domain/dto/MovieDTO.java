package com.example.demo.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MovieDTO {

	private int movieId;
	private String movieName;
	private String movieTime;
	private String movieInfo;
	private String movieType;
	private String movieAct;
	private String movieImg;
	private String movieDic;
	private String movieRelease;
	private int movieRating;



}