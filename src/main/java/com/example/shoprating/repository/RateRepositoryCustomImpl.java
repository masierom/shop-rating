package com.example.shoprating.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		System.out.println("++++++ Query exec ++++++"); //Debug
		List<Map<String, Object>> result = template.queryForList(sql);
		
		for(Map<String, Object> m : result) {
			tmpMap.put((String) m.get("product_id"), (Double) m.get("avg"));
		}
		
		return tmpMap;
	}

}
