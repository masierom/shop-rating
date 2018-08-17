package com.example.shoprating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shoprating.domain.Rate;
import com.example.shoprating.dto.Product;
import com.example.shoprating.repository.RateRepository;

@Service
public class RatingService {
	@Autowired
	private RateRepository repo;
	@Autowired
	private ProductService pService;
	
	public Rate setRating(String productId, String userId) {
		return null;
	}
	
	public List<Rate> getProductRating(String productId) {
		return null;
	}
	
	public List<Product> getPopular() {
		return null;
	}
}
