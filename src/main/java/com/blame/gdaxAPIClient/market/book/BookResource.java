package com.blame.gdaxAPIClient.market.book;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdaxAPIClient.GdaxAPIConstants;
import com.google.gson.Gson;

public class BookResource {
	private static final Logger logger = LogManager.getLogger(BookResource.class);
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_BOOK = "book";
	
	protected Invocation.Builder ib;
	protected String product;
	protected DetailLevel detailLevel;
	
	protected Gson gson = new Gson();

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

		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		this.product = product;
		this.detailLevel = detailLevel;
		ib = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_PRODUCTS).path(product).path(RESOURCE_PATH_BOOK)
				.queryParam("level", detailLevel.getMask())
				.request(MediaType.APPLICATION_JSON);
	}
	
	public Book getBook() {
		logger.info("Sending GET request over the resource...");
		String sResponse = ib.get().readEntity(String.class);
		return gson.fromJson(sResponse, Book.class).normalize(detailLevel);
	}
}
