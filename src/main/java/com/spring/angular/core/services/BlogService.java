package com.spring.angular.core.services;

import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.services.util.BlogEntryList;
import com.spring.angular.core.services.util.BlogList;

public interface BlogService {

	public BlogEntry createBlogEnrty(Long blogId, BlogEntry entry);
	
	public BlogList listAllBlogs();
	
	public BlogEntryList listAllBlogEntries(Long blogId);
	
	 public Blog findBlog(Long eq);
}
