package com.spring.angular.core.services;

import com.spring.angular.core.entities.BlogEntry;

public interface BlogEntryService {

	public BlogEntry findBlogEntry(Long id);
	public BlogEntry updateBlogEntry(Long id, BlogEntry data);
	public BlogEntry deleteBlogEntry(Long id);
}
