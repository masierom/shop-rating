package com.example.shoprating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shoprating.dto.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductService {
	@Autowired
	private RestTemplate rt;
	
	@HystrixCommand(fallbackMethod="defaultProduct")
	public Product getProductById(String productId) {
		Product product = rt.getForObject(
				"http://shop-catalog/api/products/{productId}", Product.class, productId);
		
		return product;
	}
	
	public Product defaultProduct(String productId) {
		Product product = new Product();
		product.setId("0000");
		product.setTitle("Unavaible");
		product.setAvailability(0);
		return product;
	}
}
