package com.spring.angular.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.spring.angular.core.entities.Blog;
import com.spring.angular.core.repositories.BlogRepo;

@Repository
public class JpaBlog implements BlogRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Blog> findBlogsByAccount(Long accountId) {
		Query query = em.createQuery("SELECT b FROM Blog b where b.owner.id=?1");
		query.setParameter(1, accountId);
		
		return query.getResultList();
	}

	@Override
	public Blog findBlogById(Long id) {
		return em.find(Blog.class, id);
	}

	@Override
	public Blog findBlogByTitle(String title) {
		Query query = em.createQuery("SELECT b FROM Blog b where b.title=?1");
		query.setParameter(1, title);
		
		List<Blog> blogs = query.getResultList();
		
        if(blogs.isEmpty()) {
            return null;
        } else {
            return blogs.get(0);
        }
	}

	@Override
	public Blog createBlog(Blog data) {
		em.persist(data);
		return data;
	}

	@Override
	public List<Blog> findAllBlogs() {
		  Query query = em.createQuery("SELECT b from Blog b");
	        return query.getResultList();
	}

}
