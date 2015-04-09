package com.spring.angular.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.angular.core.entities.Account;
import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.repositories.AccountRepo;
import com.spring.angular.core.repositories.BlogRepo;
import com.spring.angular.core.services.AccountService;
import com.spring.angular.core.services.exceptions.AccountDoesNotExistException;
import com.spring.angular.core.services.exceptions.BlogExistsException;
import com.spring.angular.core.services.util.AccountList;
import com.spring.angular.core.services.util.BlogList;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	BlogRepo blogRepo;

	@Override
	public Account createAccount(Account data) {
		return accountRepo.createAccount(data);
	}

	@Override
	public Blog createBlog(Long accountId, Blog data) {
		Blog blogSameTitle = blogRepo.findBlogByTitle(data.getTitle());

		if (blogSameTitle != null) {
			throw new BlogExistsException();
		}

		Account account = accountRepo.findAccount(accountId);

		Blog blog;

		if (account != null) {
			blog = blogRepo.createBlog(data);
			blog.setOwner(account);
		} else {
			throw new AccountDoesNotExistException();
		}

		return blog;
	}

	@Override
	public Account findAccount(Long accountId) {
		return accountRepo.findAccount(accountId);
	}

	@Override
	public AccountList findAllAccounts() {
		return new AccountList(accountRepo.findAllAccounts());
	}

	@Override
	public Account findByAccountName(String name) {
		return accountRepo.findAccountByName(name);
	}

	@Override
	public BlogList findAllBlogsForAccount(Long accountId) {
		Account account = accountRepo.findAccount(accountId);
		
		if (account != null){
			return new BlogList(blogRepo.findBlogsByAccount(accountId));
		}
		else{
			throw new AccountDoesNotExistException();
		}
		
	}

}
