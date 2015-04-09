package com.spring.angular.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.repositories.BlogEntryRepo;
import com.spring.angular.core.services.BlogEntryService;

@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

	@Autowired
	BlogEntryRepo blogEntryRepo;
	
	@Override
	public BlogEntry findBlogEntry(Long id) {
		return blogEntryRepo.findBlogEntry(id);
	}

	@Override
	public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
		return blogEntryRepo.updateBlogEntry(id, data);
	}

	@Override
	public BlogEntry deleteBlogEntry(Long id) {
		return blogEntryRepo.deleteBlogEntry(id);
	}

}
