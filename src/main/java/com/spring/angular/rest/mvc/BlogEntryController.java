package com.spring.angular.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.services.BlogEntryService;
import com.spring.angular.rest.assembler.BlogEntryResourceAssembler;
import com.spring.angular.rest.resource.BlogEntryResource;

@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {
	
	@Autowired
	BlogEntryService blogEntryService;
	
    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntry(
            @PathVariable Long blogEntryId) {
        BlogEntry entry = blogEntryService.findBlogEntry(blogEntryId);
        if(entry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAssembler().toResource(entry);
            return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<BlogEntryResource> deleteBlogEntry(
            @PathVariable Long blogEntryId) {
        BlogEntry entry = blogEntryService.deleteBlogEntry(blogEntryId);
        if(entry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAssembler().toResource(entry);
            return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.PUT)
    public ResponseEntity<BlogEntryResource> updateBlogEntry(
            @PathVariable Long blogEntryId, @RequestBody BlogEntryResource sentBlogEntry) {
        BlogEntry entry = blogEntryService.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());
        if(entry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAssembler().toResource(entry);
            return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

	@RequestMapping(value="/showmessage", method = RequestMethod.GET)
	public String confirmMVC(Model model){
		model.addAttribute("message", "Hey, We are here!!");
		return "showMessage";
	}	

	@RequestMapping(value="/createBlog", method = RequestMethod.POST)
	public @ResponseBody BlogEntry createBlog(@RequestBody BlogEntry entry){
		return entry;
	}


}
