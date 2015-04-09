package com.spring.angular.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.repositories.BlogEntryRepo;
import com.spring.angular.core.repositories.BlogRepo;
import com.spring.angular.core.services.BlogService;
import com.spring.angular.core.services.exceptions.BlogNotFoundException;
import com.spring.angular.core.services.util.BlogEntryList;
import com.spring.angular.core.services.util.BlogList;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogEntryRepo blogEntryRepo;
	
	@Autowired 
	BlogRepo blogRepo;
	

	@Override
	public BlogList listAllBlogs() {
		return new BlogList(blogRepo.findAllBlogs());
	}

	@Override
	public BlogEntryList listAllBlogEntries(Long blogId) {
		  Blog blog = blogRepo.findBlogById(blogId);
		  
	        if(blog == null)
	        {
	            throw new BlogNotFoundException();
	        }
	        return new BlogEntryList(blogId, blogEntryRepo.findByBlogId(blogId));	}


	@Override
	public BlogEntry createBlogEnrty(Long blogId, BlogEntry data) {
        Blog blog = blogRepo.findBlogById(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        BlogEntry entry = blogEntryRepo.createBlogEntry(data);
        entry.setBlog(blog);
        return entry;
	}

	@Override
	public Blog findBlog(Long eq) {
	      Blog blog = blogRepo.findBlogById(eq);
	        if(blog == null)
	        {
	            throw new BlogNotFoundException();
	        }else{
	        	return blog;
	        }
	  	}

}
