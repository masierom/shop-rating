package com.example.shoprating.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shoprating.domain.Rate;
import com.example.shoprating.dto.Product;
import com.example.shoprating.dto.RateRequest;
import com.example.shoprating.service.RatingService;

@Controller
public class RatingController {
	@Autowired
	private RatingService service;
	
	@PostMapping("/api/ratings/{productId}/{userId}")
	public @ResponseBody Rate setRating(@PathVariable String productId, 
			@PathVariable String userId,
			@RequestBody RateRequest rateReq) {	
		
		return service.setRating(productId, userId, rateReq);
	}
	
	@GetMapping("/api/ratings/{productId}")
	public @ResponseBody List<Rate> getProductRating(@PathVariable String productId) {
		return service.getProductRating(productId);
	}
	
	@GetMapping("/api/ratings/popular")
	public @ResponseBody List<Product> getPopular() {
		return service.getPopular();
	}
}
