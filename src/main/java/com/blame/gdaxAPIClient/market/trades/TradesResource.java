package com.blame.gdaxAPIClient.market.trades;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdaxAPIClient.GdaxAPIConstants;

public class TradesResource {
	private static final Logger logger = LogManager.getLogger(TradesResource.class);
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_BOOK = "trades";
	
	protected Invocation.Builder ib;
	protected String product;
	
	protected String cursorForOlder;
	
	public TradesResource(String product) {
		super();
		this.product = product;

		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		ib = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_PRODUCTS).path(product).path(RESOURCE_PATH_BOOK)
				.request(MediaType.APPLICATION_JSON);
	}
	
	public String get() {
		logger.info("Sending GET request over the resource...");
		Response response = this.ib.get();
		this.cursorForOlder = response.getHeaderString("CB-AFTER");
		String sResponse = response.readEntity(String.class);
		return sResponse;
	}
	
	public String next() {
		Invocation.Builder tempIb = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_PRODUCTS).path(product).path(RESOURCE_PATH_BOOK)
				.queryParam("after", cursorForOlder)
				.request(MediaType.APPLICATION_JSON);

		Response response = this.ib.get();
		this.cursorForOlder = response.getHeaderString("CB-AFTER");
		String sResponse = response.readEntity(String.class);
		return sResponse;
	}
}
