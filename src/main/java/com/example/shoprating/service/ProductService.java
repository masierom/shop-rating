package com.example.shoprating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shoprating.dto.Product;

@Service
public class ProductService {
	@Autowired
	private RestTemplate rt;
	
	public Product getProductById(String productId) {
		return null;
	}
}
