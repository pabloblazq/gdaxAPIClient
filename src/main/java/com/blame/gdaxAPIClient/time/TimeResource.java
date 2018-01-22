package com.blame.gdaxAPIClient.time;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdaxAPIClient.GdaxAPIConstants;

public class TimeResource {
	private static final Logger logger = LogManager.getLogger(TimeResource.class);
	
	protected static final String RESOURCE_PATH_TIME = "time";
	
	protected Invocation.Builder ib;

	public TimeResource() {
		super();
		
		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		ib = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_TIME)
				.request(MediaType.APPLICATION_JSON);
	}
	
	public String get() {
		logger.info("Sending GET request over the resource...");
		return ib.get().readEntity(String.class);
	}
}
