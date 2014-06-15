package com.codeproof.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DozerConverter<DTO, E> {

	@Autowired
	private Mapper mapper;

	public E convertTo(DTO dto, Class<E> entity) {
		E cEntity = null;
		if (dto != null) {
			cEntity = mapper.map(dto, entity);
		}
		return cEntity;
	}

	public List<DTO> convertFrom(List<E> entityList, Class<DTO> dto) {
		List<DTO> dtoList = null;
		if (!CollectionUtils.isEmpty(entityList)) {
			dtoList = new ArrayList();
			for (E entity : entityList) {
				dtoList.add(convertFrom(entity, dto));
			}
		}
		return dtoList;
	}

	public DTO convertFrom(E entity, Class<DTO> dto) {
		DTO cDto = null;
		if (entity != null) {
			cDto = mapper.map(entity, dto);
		}
		return cDto;
	}

	public List<E> convertTo(List<DTO> dtoList, Class<E> entity) {
		List<E> cEntityList = null;
		if (!CollectionUtils.isEmpty(dtoList)) {
			cEntityList = new ArrayList();
			for (DTO dto : dtoList) {
				cEntityList.add(convertTo(dto, entity));
			}
		}
		return cEntityList;
	}
	
	public Set<DTO> convertFrom(Set<E> entityList, Class<DTO> dto) {
		Set<DTO> dtoList = null;
		if (!CollectionUtils.isEmpty(entityList)) {
			dtoList = new HashSet();
			for (E entity : entityList) {
				dtoList.add(convertFrom(entity, dto));
			}
		}
		return dtoList;
	}
	
	public Set<E> convertTo(Set<DTO> dtoList, Class<E> entity) {
		Set<E> cEntityList = null;
		if (!CollectionUtils.isEmpty(dtoList)) {
			cEntityList = new HashSet();
			for (DTO dto : dtoList) {
				cEntityList.add(convertTo(dto, entity));
			}
		}
		return cEntityList;
	}
}
