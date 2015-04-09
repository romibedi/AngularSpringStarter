package com.spring.angular.rest.mvc;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.angular.core.entities.Account;
import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.services.AccountService;
import com.spring.angular.core.services.util.AccountList;
import com.spring.angular.core.services.util.BlogList;
import com.spring.angular.rest.assembler.AccountListResourceAsm;
import com.spring.angular.rest.assembler.AccountResourceAsm;
import com.spring.angular.rest.assembler.BlogListResourceAsm;
import com.spring.angular.rest.assembler.BlogResourceAsm;
import com.spring.angular.rest.resource.AccountListResource;
import com.spring.angular.rest.resource.AccountResource;
import com.spring.angular.rest.resource.BlogListResource;
import com.spring.angular.rest.resource.BlogResource;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccountListResource> findAllAccounts(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password) {
		AccountList list = null;
		if (name == null) {
			list = accountService.findAllAccounts();
		} else {
			Account account = accountService.findByAccountName(name);
			list = new AccountList(new ArrayList<Account>());
			if (account != null) {
				if (password != null) {
					if (account.getPassword().equals(password)) {
						list = new AccountList(Arrays.asList(account));
					}
				} else {
					list = new AccountList(Arrays.asList(account));
				}
			}
		}
		AccountListResource res = new AccountListResourceAsm().toResource(list);
		return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces={"application/xml", "application/json"}, 
			consumes={"application/xml"})
	public ResponseEntity<AccountResource> createAccount(
			@Valid @RequestBody AccountResource sentAccount)  {
		Account createdAccount = accountService.createAccount(sentAccount
				.toAccount());
		AccountResource res = new AccountResourceAsm()
				.toResource(createdAccount);
		HttpHeaders headers = new HttpHeaders();
		System.out.println("account name is - " + sentAccount.getName());
		
		//if (sentAccount.getName() == null) {
			//throw new IllegalArgumentException();
		//}

		headers.setLocation(URI.create(res.getLink("self").getHref()));
		return new ResponseEntity<AccountResource>(res, headers,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(
			@PathVariable Long accountId) {
		Account account = accountService.findAccount(accountId);
		if (account != null) {
			AccountResource res = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
	public ResponseEntity<BlogResource> createBlog(
			@PathVariable Long accountId, @RequestBody BlogResource res) 
	{

		// test MethodArgumentNotValidException on this method by providing wrong @RequestBody 
		Account account = accountService.findAccount(accountId);
		if (account != null) {
			Blog createdBlog = accountService.createBlog(accountId,
					res.toBlog());
			BlogResource createdBlogRes = new BlogResourceAsm()
					.toResource(createdBlog);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdBlogRes.getLink("self")
					.getHref()));
			return new ResponseEntity<BlogResource>(createdBlogRes, headers,
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<BlogResource>(HttpStatus.NOT_FOUND);
		}
	
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.GET)
	public ResponseEntity<BlogListResource> findAllBlogs(
			@PathVariable Long accountId) {
        
            BlogList blogList = accountService.findAllBlogsForAccount(accountId);
            BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
        
	}
}
