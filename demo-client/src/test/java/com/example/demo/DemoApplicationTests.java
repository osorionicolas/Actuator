package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controllers.MainController;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class DemoApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@Test
	public void getModelAndView() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/main"))
						  					  .andDo(MockMvcResultHandlers.print())
						  					  .andExpect(MockMvcResultMatchers.view().name("main"))
						  					  .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getMap() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/map"))
						  					  .andExpect(MockMvcResultMatchers.jsonPath("$.key").exists())
						  					  .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("value"))
						  					  .andExpect(MockMvcResultMatchers.status().isOk())
						  					  .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
