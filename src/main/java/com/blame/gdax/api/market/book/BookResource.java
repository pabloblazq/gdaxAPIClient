package com.blame.gdax.api.market.book;

import javax.ws.rs.client.Invocation;

import com.blame.gdax.resource.Resource;

public class BookResource extends Resource {
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_BOOK = "book";
	
	protected Invocation.Builder invocationBuilder;
	protected String product;
	protected DetailLevel detailLevel;
	
	public enum DetailLevel {
		LEVEL_1("1"), LEVEL_2("2"), LEVEL_3("3");
		
		private final String mask;
	    private DetailLevel(String mask) {
	        this.mask = mask;
	    }

	    public String getMask() {
	        return mask;
	    }
	}

	public BookResource(String product, DetailLevel detailLevel) {
		super(new StringBuilder(RESOURCE_PATH_PRODUCTS).append("/").append(product).append("/").append(RESOURCE_PATH_BOOK).toString());

		this.product = product;
		this.detailLevel = detailLevel;
		invocationBuilder = getInvocationBuilder("level", detailLevel.getMask());
	}
	
	public Book getBook() {
		logger.info("Getting book of orders ...");
		String sResponse = invocationBuilder.get().readEntity(String.class);
		logger.debug("Transforming GDAX response into Book object ...");
		Book b = gson.fromJson(sResponse, Book.class).normalize(detailLevel);
		logger.debug("Book object ready.");
		return b;
	}
}
