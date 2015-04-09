package com.spring.angular.rest.mvc;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.services.BlogService;
import com.spring.angular.core.services.util.BlogEntryList;
import com.spring.angular.core.services.util.BlogList;
import com.spring.angular.rest.assembler.BlogEntryListResourceAsm;
import com.spring.angular.rest.assembler.BlogEntryResourceAssembler;
import com.spring.angular.rest.assembler.BlogListResourceAsm;
import com.spring.angular.rest.assembler.BlogResourceAsm;
import com.spring.angular.rest.resource.BlogEntryListResource;
import com.spring.angular.rest.resource.BlogEntryResource;
import com.spring.angular.rest.resource.BlogListResource;
import com.spring.angular.rest.resource.BlogResource;

@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

	@Autowired
	BlogService blogService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<BlogListResource> findAllBlogs() {

		BlogList blogList = blogService.listAllBlogs();

		if (CollectionUtils.isEmpty((Collection<?>) blogList)) {
			return new ResponseEntity<BlogListResource>(HttpStatus.NOT_FOUND);

		} else {
			BlogListResource blogListRes = new BlogListResourceAsm()
					.toResource(blogList);
			return new ResponseEntity<BlogListResource>(blogListRes,
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
		Blog blog = blogService.findBlog(blogId);

		if (blog != null) {
			BlogResource blogRes = new BlogResourceAsm().toResource(blog);
			return new ResponseEntity<BlogResource>(blogRes, HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.POST)
	public ResponseEntity<BlogEntryResource> createBlogEntry(
			@PathVariable Long blogId,
			@RequestBody BlogEntryResource sentBlogEntry) {
		BlogEntry entry = blogService.createBlogEnrty(blogId,
				sentBlogEntry.toBlogEntry());

		if (entry != null) {
			BlogEntryResource blogEntryRes = new BlogEntryResourceAssembler()
					.toResource(entry);
			return new ResponseEntity<BlogEntryResource>(blogEntryRes,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{blogId}/blog-entries")
	public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
			@PathVariable Long blogId) {

		BlogEntryList blogEntryList = blogService.listAllBlogEntries(blogId);

		if (CollectionUtils.isEmpty((Collection<?>) blogEntryList)) {
			return new ResponseEntity<BlogEntryListResource>(
					HttpStatus.NOT_FOUND);
		} else {
			BlogEntryListResource res = new BlogEntryListResourceAsm()
					.toResource(blogEntryList);
			return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
		}
	}
}
