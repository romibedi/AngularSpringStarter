package com.spring.angular.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Repository;

import com.spring.angular.core.entities.Account;
import com.spring.angular.core.repositories.AccountRepo;

@Repository
public class JpaAccount implements AccountRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Account> findAllAccounts() {
		Query query = em.createQuery("SELECT a from Account a");
		return query.getResultList();
	}

	@Override
	public Account findAccount(Long id) {
		return em.find(Account.class, id);
	}

	@Override
	public Account findAccountByName(String name) {
		Query query = em.createQuery("SELECT a from Account a where a.name=?1");
		query.setParameter(1, name);
		
		List<Account> accounts =  query.getResultList();
		
		if (accounts.size() == 0){
			return null;
		}else{
			return accounts.get(0);
		}
	}

	@Override
	public Account createAccount(Account data) {
		em.persist(data);
		
		return data;
	}

}
