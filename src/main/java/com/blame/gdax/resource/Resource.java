package com.blame.gdax.resource;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdax.api.GdaxAPIConstants;
import com.blame.gdax.api.market.book.BookResource;
import com.google.gson.Gson;

public abstract class Resource {
	protected static Gson gson = new Gson();

	protected Logger logger = LogManager.getLogger(BookResource.class);

	protected WebTarget webTarget;

	public Resource(String resourcePath) {
		super();

		logger = LogManager.getLogger(this.getClass());
		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		
		webTarget = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(resourcePath);
		logger.info("Resource built.");
	}

	protected Invocation.Builder getInvocationBuilder() {
		return webTarget.request(MediaType.APPLICATION_JSON);
	}
	
	protected Invocation.Builder getInvocationBuilder(String paramName, String paramValue) {
		return webTarget
				.queryParam(paramName, paramValue)
				.request(MediaType.APPLICATION_JSON);
	}
}
