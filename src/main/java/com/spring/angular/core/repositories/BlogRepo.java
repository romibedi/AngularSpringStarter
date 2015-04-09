package com.spring.angular.core.repositories;

import java.util.List;

import com.spring.angular.core.entities.Blog;

public interface BlogRepo {

	public List<Blog> findBlogsByAccount(Long accountId);
	
	public Blog findBlogById(Long id);
	
	public Blog findBlogByTitle(String title);
	
	public Blog createBlog(Blog data);
	
	public List<Blog> findAllBlogs();
	
}
