package com.spring.angular.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.angular.core.entities.Account;
import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.repositories.AccountRepo;
import com.spring.angular.core.repositories.BlogEntryRepo;
import com.spring.angular.core.repositories.BlogRepo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")

public class BlogEntryRepoTest {
	   @Autowired
	    private AccountRepo repo;

	    @Autowired
		BlogRepo blogRepo;

	    @Autowired
		BlogEntryRepo blogEntryRepo;

	    private Account account;
	    
	    private Blog blog;
	    
	    private BlogEntry blogEntry;
	    
	    // Create an account and create a blog in that account as a setup for all test methods
	    @Before
	    @Transactional
	    @Rollback(false)
	    public void setup()
	    {
	        account = new Account();
	        account.setName("name");
	        account.setPassword("password");
	        repo.createAccount(account);
	
	        Blog data = new Blog();
	        data.setTitle("Kitchen Blog");
	        data.setOwner(account); 
	    	
	        blog = blogRepo.createBlog(data);
	        
	        blogEntry = new BlogEntry();
	        
	        blogEntry.setTitle("How to make an omellete");
	        blogEntry.setContent("Omelette Recipe: bla bla bla");
	        blogEntry.setBlog(blog);
	    	
	    	blogEntryRepo.createBlogEntry(blogEntry);
	    	        
	    }
	
	    // public BlogEntry findBlogEntry(Long id);
	    @Test
	    @Transactional
	    public void findBlogEntry()
	    {
	    	assertEquals(blogEntry, blogEntryRepo.findBlogEntry(blogEntry.getId()));
	    }
	    
	    // public BlogEntry updateBlogEntry(Long id, BlogEntry data);
	    @Test
	    @Transactional
	    public void updateBlogEntry()
	    {
	    	blogEntry.setTitle("Updated Title");
	    	
	    	BlogEntry updatedEntry = blogEntryRepo.updateBlogEntry(blogEntry.getId(), blogEntry);
	    		
	    	assertEquals("Updated Title", blogEntry.getTitle());
	    }	    
	    
	    // public BlogEntry deleteBlogEntry(Long id);
	    @Test
	    @Transactional
	    public void deleteBlogEntry()
	    {
	    	// TO BE DONE
	    	//blogEntryRepo.deleteBlogEntry(blogEntry.getId());
	    	
	    	//assertNull(blogEntry);
	    }
}
