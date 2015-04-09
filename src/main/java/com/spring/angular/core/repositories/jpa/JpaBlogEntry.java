package com.spring.angular.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.spring.angular.core.entities.BlogEntry;
import com.spring.angular.core.repositories.BlogEntryRepo;

@Repository
public class JpaBlogEntry implements BlogEntryRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public BlogEntry findBlogEntry(Long blogEntryId) {
		return em.find(BlogEntry.class, blogEntryId);
	}

	@Override
	public BlogEntry deleteBlogEntry(Long blogEntryId) {
		BlogEntry entry = em.find(BlogEntry.class, blogEntryId);
		em.remove(entry);

		return entry;

	}

	@Override
	public BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry data) {
		BlogEntry entry = em.find(BlogEntry.class, blogEntryId);

		entry.setContent(data.getContent());
		entry.setTitle(data.getTitle());

		return entry;
	}

	@Override
	public List<BlogEntry> findByBlogId(Long blogId) {
		Query query = em
				.createQuery("SELECT b FROM BlogEntry b WHERE b.blog.id=?1");
		query.setParameter(1, blogId);
		return query.getResultList();
	}

	@Override
	public BlogEntry createBlogEntry(BlogEntry data) {
		em.persist(data);

		return data;
	}

}
