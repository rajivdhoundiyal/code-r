package com.codeproof.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproof.util.DozerConverter;

public abstract class AbstractBusinessService<DTO, E> {
	
	@Autowired
	protected DozerConverter<DTO, E> dozerConverter;
	
	public AbstractBusinessService() {
		dozerConverter = new DozerConverter<DTO, E>();
	}

	public DozerConverter<DTO, E> getDozerConverter() {
		return dozerConverter;
	}

	public void setDozerConverter(DozerConverter<DTO, E> dozerConverter) {
		this.dozerConverter = dozerConverter;
	}
	
}
