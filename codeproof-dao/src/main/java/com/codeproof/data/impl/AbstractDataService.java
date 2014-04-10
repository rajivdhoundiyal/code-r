package com.codeproof.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractDataService<T> {

	@Autowired
	MongoTemplate mongoTemplate;

	public T find(String id, Class<T> clazz) {
		return mongoTemplate.findById(id, clazz);
	}

	public T save(T t) {
		mongoTemplate.save(t);
		return t;
	}

	public T update(T t) {
		mongoTemplate.save(t);
		return t;
	}
	
	protected abstract Class<T> getReferenceClass();
	
}
