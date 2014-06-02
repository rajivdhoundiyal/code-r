package com.codeproof.data.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDataService<E> {

	@Autowired
	MongoTemplate mongoTemplate;
	
	/*@Autowired*/
	SessionFactory sessionFactory;

	@Transactional
	public E find(String id) {
		//return (E) getSession().get(getReferenceClass(), id);
		return mongoTemplate.findById(id, getReferenceClass());
	}

	@Transactional
	public void save(E e) {
		mongoTemplate.save(e);
		//getSession().save(e);
	}

	@Transactional
	public void update(E e) {
		mongoTemplate.save(e);
		//getSession().saveOrUpdate(e);
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	protected abstract Class<E> getReferenceClass();
	
}
