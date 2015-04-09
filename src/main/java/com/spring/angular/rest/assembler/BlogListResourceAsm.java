package com.spring.angular.rest.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.spring.angular.core.services.util.BlogList;
import com.spring.angular.rest.mvc.BlogController;
import com.spring.angular.rest.resource.BlogListResource;

/**
 * Created by Chris on 7/1/14.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm()
    {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource res = new BlogListResource();
        res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return res;
    }
}
