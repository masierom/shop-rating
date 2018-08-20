package com.example.shoprating.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class RateRepositoryCustomImpl implements RateRepositoryCustom {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public HashMap<String, Double> getAverageListOfProducts() {
		HashMap<String, Double> tmpMap = new HashMap<String, Double>();
		String sql = "select product_id, avg(rate) as 'avg' " +
		"from rate " +
		"group by (product_id) ";
		//+ "order by (avg) desc";
		
		try {
			System.out.println("++++++ Query exec ++++++"); //Debug
			List<Map<String, Object>> result = template.queryForList(sql);
			
			for(Map<String, Object> m : result) {
				tmpMap.put((String) m.get("product_id"), (Double) m.get("avg"));
			}
			
			return tmpMap;
		}
		catch (EmptyResultDataAccessException e){
			System.out.println("ERROR - empty result: " + e.getMessage());
			
			return new HashMap<String, Double>();
		}
		catch(Exception e) {
			System.out.println("ERROR - exception: " + e.getMessage());
			
			return null;
		}
	}

	@Override
	public String getIdByUserIdandProductID(String userId, String productId) {
		String sql = "select id " +
				"from rate " +
				"where product_id='" + productId + "' " +
				"and user_id='" + userId + "'";
		
		try {
			System.out.println("++++++ Query exec ++++++"); //Debug
			String result = template.queryForObject(sql, String.class);
			
			return result;
		}
		catch (EmptyResultDataAccessException e){
			System.out.println("ERROR - empty result: " + e.getMessage());
			
			return null;
		}
		catch(Exception e) {
			System.out.println("ERROR - exception: " + e.getMessage());
			
			return null;
		}
	}

}
