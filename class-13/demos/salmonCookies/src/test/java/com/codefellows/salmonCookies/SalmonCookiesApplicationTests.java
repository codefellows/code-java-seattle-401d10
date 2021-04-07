package com.codefellows.salmonCookies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class SalmonCookiesApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void testHomePage() throws Exception {
//		make sure there are two forms
//		h1 with Salmon Cookies
		mockMvc.perform(get("/"))
//				.andDo(print())
				.andExpect(content().string(containsString("<h1>Salmon Cookies</h1>")))
				.andExpect(content().string(containsString(" <form action=\"/cookie-store\" method=\"POST\">")))
				.andExpect(status().isOk());
	}

//	@Test
//	void testPostCookieStore() throws Exception {
//		class CookieStoreFormData {
//			String name = "seattle";
//			int maxSales = 1;
//			int minSales = 1;
//		}
//		CookieStoreFormData formData = new CookieStoreFormData();
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(formData);
//
//		mockMvc.perform(
//				post("/cookie-store" )
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(json)
//		)
//				.andExpect(content().string(containsString("<td>seattle</td>")));
//
////		 String namePotato,
////            int minSales,
////            int maxSales
//	}

}
