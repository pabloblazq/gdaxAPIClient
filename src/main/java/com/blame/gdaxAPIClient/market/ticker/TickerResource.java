package com.blame.gdaxAPIClient.market.ticker;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdaxAPIClient.GdaxAPIConstants;

public class TickerResource {
	private static final Logger logger = LogManager.getLogger(TickerResource.class);
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_TICKER = "ticker";
	
	protected Invocation.Builder ib;
	protected String product;

	public TickerResource(String product) {
		super();
		this.product = product;
		
		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		ib = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_PRODUCTS).path(product).path(RESOURCE_PATH_TICKER)
				.request(MediaType.APPLICATION_JSON);
	}
	
	public String get() {
		logger.info("Sending GET request over the resource...");
		return ib.get().readEntity(String.class);
	}
}
