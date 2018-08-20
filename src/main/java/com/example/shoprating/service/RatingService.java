package com.example.shoprating.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoprating.domain.Rate;
import com.example.shoprating.dto.Product;
import com.example.shoprating.dto.ProductWithRate;
import com.example.shoprating.dto.RateRequest;
import com.example.shoprating.repository.RateRepository;

@Service
public class RatingService {
	@Autowired
	private RateRepository repo;
	@Autowired
	private ProductService pService;
	
	public Rate setRating(String productId, String userId, RateRequest rateReq) {
		String oldId = repo.getIdByUserIdandProductID(userId, productId);
		
		if(oldId == null) {			
			Rate newRate = new Rate();
			newRate.setProductId(productId);
			newRate.setUserId(userId);
			newRate.setRate(rateReq.getRate());
			newRate.setComment(rateReq.getComment());
		
			return repo.save(newRate);
		} else {
			Optional<Rate> sr = repo.findById(oldId);
			sr.get().setRate(rateReq.getRate());
			sr.get().setComment(rateReq.getComment());
			
			return repo.save(sr.get());
		}
	}
	
	public List<Rate> getProductRating(String productId) {
		return repo.findByProductId(productId);
	}
	
	public List<ProductWithRate> getPopular() {
		List<ProductWithRate> products = new ArrayList<ProductWithRate>();
		Map<String, Double> avgMap = repo.getAverageListOfProducts();
		
		avgMap.entrySet()
	    .stream()
	    .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
	    .limit(5)
	    .forEach(x -> {
	    	ProductWithRate pwr = new ProductWithRate();
	    	Product p = pService.getProductById(x.getKey());
	    	pwr.setProductId(p.getId());
	    	pwr.setTitle(p.getTitle());
	    	pwr.setPrice(p.getPrice());
	    	pwr.setAverageRate(x.getValue());
	    	
	    	products.add(pwr);
	    });
		
		return products;
	}
}
