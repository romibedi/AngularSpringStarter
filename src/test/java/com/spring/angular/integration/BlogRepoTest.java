package com.spring.angular.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")

public class BlogRepoTest {
	   @Autowired
	    private AccountRepo repo;

	    @Autowired
		BlogRepo blogRepo;

	    @Autowired
		BlogEntryRepo blogEntryRepo;

	    private Account account;
	    
	    private Blog blog;
	    
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
	    }
	    
	    // public BlogEntry createBlogEnrty(Long blogId, BlogEntry entry);
	    @Test
	    @Transactional
	    public void createBlogEnrty()
	    {
	    	BlogEntry entry = new BlogEntry();
	    	entry.setTitle("How to cook masala omelette");
	    	entry.setContent("breal two eggs, put some chilli and some onions.");
	    	
	    	BlogEntry blogEntry = blogEntryRepo.createBlogEntry(entry);
	    	
	    	blogEntry.setBlog(blog);
	    	
	    	assertEquals(blog, blogEntry.getBlog());
	    	assertEquals(blogEntry.getTitle(),"How to cook masala omelette");
	    	
	    }
		// public BlogList listAllBlogs();
	    @Test
	    @Transactional
	    public void listAllBlogs()
	    {
	    	List<Blog> blogList = blogRepo.findAllBlogs();
	    	
	    	assertEquals(blog, blogList.get(0));
	    	
	    }
	    
		// public BlogEntryList listAllBlogEntries(Long blogId);
	    @Test
	    @Transactional
	    public void listAllBlogEntries()
	    {
	    	BlogEntry entry = new BlogEntry();
	    	entry.setTitle("How to cook masala omelette");
	    	entry.setContent("breal two eggs, put some chilli and some onions.");
	    	
	    	BlogEntry blogEntry = blogEntryRepo.createBlogEntry(entry);
	    	
	    	blogEntry.setBlog(blog);
	    	
	    	List<BlogEntry> blogEntries = blogEntryRepo.findByBlogId(blog.getId());
	    	
	    	assertEquals(blogEntry, blogEntries.get(0));
	    }
}
