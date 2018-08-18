package com.example.shoprating;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopRatingApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test1_PostRating() throws Exception {
		RequestBuilder postRequest = MockMvcRequestBuilders.post("/api/ratings/{productId}/{userId}",
				"cbdbc72c-977f-450b-9b0e-b6eca9a7f200","33")
				.content("{\"rate\": 5, \"comment\" : \"Very nice!\" }")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8);
		
		mockMvc.perform(postRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("Very nice!"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value("33"));
	}
	
	@Test
	public void test2_GetRating() throws Exception {
		RequestBuilder getRequest = MockMvcRequestBuilders.get("/api/ratings/{productId}",
				"cbdbc72c-977f-450b-9b0e-b6eca9a7f200")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8);
		
		mockMvc.perform(getRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].rate").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("Very nice!"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value("33"));
	}

	
	@Test
	public void test3_GetPopular() throws Exception {
		RequestBuilder getRequest = MockMvcRequestBuilders.get("/api/ratings/popular")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8);
		
		mockMvc.perform(getRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Iron steam")) //Due to previous tests
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Chair"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].averageRate").value(5));
	}
}
