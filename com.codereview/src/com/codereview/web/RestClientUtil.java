package com.codereview.web;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class RestClientUtil<T> {

	private WebResource service;
	private String baseUrl = null;

	public RestClientUtil(String baseUrl) {
		this.baseUrl = baseUrl;
		ClientConfig config = new DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		Client client = Client.create(config);
		service = client.resource(getBaseURI());
	}

	public T doGet(String path, String contentType, String acceptType,
			Class<T> returnClazz) {
		return getBuilder(path, contentType, acceptType).get(returnClazz);
	}

	public T doPost(String path, String contentType, String acceptType,
			Object requestObject, Class<T> returnClazz) {
		return getBuilder(path, contentType, acceptType).post(returnClazz,
				requestObject);
	}

	public T doPut(String path, String contentType, String acceptType,
			Object requestObject, Class<T> returnClazz) {
		return getBuilder(path, contentType, acceptType).put(returnClazz,
				requestObject);
	}

	private Builder getBuilder(String path, String contentType,
			String acceptType) {
		return service.path(path).type(contentType).accept(acceptType);
	}

	public T doDelete(String path, String contentType, String acceptType,
			Object requestObject, Class<T> returnClazz) {
		return getBuilder(path, contentType, acceptType).delete(returnClazz,
				requestObject);
	}

	private URI getBaseURI() {
		return UriBuilder.fromUri(baseUrl).build();
	}

}
