package com.blame.gdaxAPIClient.market.book;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdaxAPIClient.GDAXAPIConstants;

public class BookResource {
	private static final Logger logger = LogManager.getLogger(BookResource.class);
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_BOOK = "book";
	
	protected Invocation.Builder ib;
	protected String product;
	
	public enum DetailLevel {
		LEVEL_1(1), LEVEL_2(2), LEVEL_3(3);
		
		private final int mask;
	    private DetailLevel(int mask) {
	        this.mask = mask;
	    }

	    public int getMask() {
	        return mask;
	    }
	}

	public BookResource(String product, DetailLevel detailLevel) {
		super();
		this.product = product;

		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		ib = ClientBuilder
				.newClient()
				.target(GDAXAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_PRODUCTS).path(product).path(RESOURCE_PATH_BOOK)
				.queryParam("level", detailLevel.getMask())
				.request(MediaType.APPLICATION_JSON);
	}
	
	public String get() {
		logger.info("Sending GET request over the resource...");
		return ib.get().readEntity(String.class);
	}
}
