package com.spring.angular.core.repositories;

import java.util.List;

import com.spring.angular.core.entities.Account;

public interface AccountRepo {

    public List<Account> findAllAccounts();
    public Account findAccount(Long id);
    public Account findAccountByName(String name);
    public Account createAccount(Account data);
	
}
