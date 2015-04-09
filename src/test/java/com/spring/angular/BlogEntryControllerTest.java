package com.spring.angular;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.services.BlogEntryService;
import com.spring.angular.rest.mvc.BlogEntryController;


public class BlogEntryControllerTest {

	
	@InjectMocks
	BlogEntryController blogEntryController;
	
	@Mock
	BlogEntryService blogEntryService;
	
	private MockMvc mockMvc;  //from Spring Test FW
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(blogEntryController).build();
	}

	@Test
	public void test() throws Throwable{
		mockMvc.perform(post("/rest/blog-entries/createBlog")
				.content("{\"title\":\"My first blog\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title", is("My first blog")))
				.andDo(print());	
	}
	

	@SuppressWarnings("deprecation")
	@Test
	public void getExistingBlogEntry() throws Exception{
		BlogEntry entry = new BlogEntry();
		entry.setId(1L);
		entry.setTitle("New Title");
		
		when(blogEntryService.findBlogEntry(1L)).thenReturn(entry);
		
		mockMvc.perform(get("/rest/blog-entries/1")).
				andExpect(jsonPath("$.title", is(entry.getTitle()))).
				andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1")))).
				andExpect(status().isOk()).andDo(print());
		}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getNonExistingBlogEntry() throws Exception{

		when(blogEntryService.findBlogEntry(1L)).thenReturn(null);
		
		mockMvc.perform(get("/rest/blog-entries/1")).
				andExpect(status().isNotFound()).andDo(print());
	}
}
