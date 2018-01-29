package com.blame.gdax.api.resource.time;

import javax.ws.rs.client.Invocation;

import com.blame.gdax.api.resource.Resource;

public class TimeResource extends Resource {
	protected static final String RESOURCE_PATH_TIME = "time";
	
	protected Invocation.Builder invocationBuilder;

	public TimeResource() {
		super(RESOURCE_PATH_TIME);
		
		invocationBuilder = getInvocationBuilder();
	}
	
	public String getTime() {
		logger.info("Getting time ...");
		return invocationBuilder.get().readEntity(String.class);
	}
}
