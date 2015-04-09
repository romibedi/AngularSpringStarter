package com.spring.angular.rest.assembler;

import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.rest.mvc.BlogEntryController;
import com.spring.angular.rest.resource.BlogEntryResource;

public class BlogEntryResourceAssembler extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource>{

	@Override
	public BlogEntryResource toResource(BlogEntry entry) {
		BlogEntryResource res = new BlogEntryResource();
		res.setContent(entry.getContent());
		res.setTitle(entry.getTitle());
		Link link = linkTo(methodOn(BlogEntryController.class).getBlogEntry(entry.getId())).withSelfRel();
		
		res.add(link);
		
		return res;
	}

	public BlogEntryResourceAssembler() {
		super(BlogEntryController.class, BlogEntryResource.class);
	}

}
