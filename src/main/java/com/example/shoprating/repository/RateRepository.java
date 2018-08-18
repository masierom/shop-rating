package com.example.shoprating.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.shoprating.domain.Rate;

public interface RateRepository extends CrudRepository<Rate, String>, RateRepositoryCustom{
	public List<Rate> findByProductId(String productId);
}
