package com.spring.angular.core.services;

import com.spring.angular.core.entities.Account;
import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.services.util.AccountList;
import com.spring.angular.core.services.util.BlogList;

public interface AccountService {
	
	public Account createAccount(Account data);
	public Blog createBlog(Long accountId, Blog data);

	public Account findAccount(Long accountId);
	public AccountList findAllAccounts();
	public Account findByAccountName(String name);
	
	public BlogList findAllBlogsForAccount(Long accountId);
	

}
