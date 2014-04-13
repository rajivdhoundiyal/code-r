package com.codeproof.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractDataService<T> {

	@Autowired
	MongoTemplate mongoTemplate;

	public T find(String id) {
		return mongoTemplate.findById(id, getReferenceClass());
	}

	public void save(T t) {
		mongoTemplate.save(t);
	}

	public void update(T t) {
		mongoTemplate.save(t);
	}
	
	protected abstract Class<T> getReferenceClass();
	
}
