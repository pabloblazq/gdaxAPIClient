package com.blame.gdax.api.market.trades;

import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import com.blame.gdax.resource.Resource;
import com.google.gson.reflect.TypeToken;

public class TradesResource extends Resource {
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_TRADES = "trades";

	protected static Type tradeCollectionType = new TypeToken<Collection<Trade>>(){}.getType();

	protected Invocation.Builder invocationBuilder;
	protected String product;
	
	protected String cursorBefore;
	protected String cursorAfter;
	
	public TradesResource(String product) {
		super(new StringBuilder(RESOURCE_PATH_PRODUCTS).append("/").append(product).append("/").append(RESOURCE_PATH_TRADES).toString());

		this.product = product;

		invocationBuilder = getInvocationBuilder();
	}
	
	public Collection<Trade> getTrades() {
		logger.info("Getting last trades ...");
		Response response = invocationBuilder.get();

		logger.debug("Registering pagination cursors ...");
		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");
		
		String sResponse = response.readEntity(String.class);

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		Collection<Trade> trades = gson.fromJson(sResponse, tradeCollectionType);
		
		logger.debug("Collection<Trade> object ready.");
		return trades;
	}
	
	public Collection<Trade> getTradesNewer() {
		logger.info("Getting newer trades ...");
		Invocation.Builder tempIb = getInvocationBuilder("before", cursorBefore);
		Response response = tempIb.get();

		logger.debug("Registering pagination cursors ...");
		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");

		String sResponse = response.readEntity(String.class);

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		Collection<Trade> trades = gson.fromJson(sResponse, tradeCollectionType);
		
		logger.debug("Collection<Trade> object ready.");
		return trades;
	}

	public Collection<Trade> getTradesOlder() {
		logger.info("Getting newer trades ...");
		Invocation.Builder tempIb = getInvocationBuilder("after", cursorAfter);
		Response response = tempIb.get();

		logger.debug("Registering pagination cursors ...");
		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		String sResponse = response.readEntity(String.class);

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		Collection<Trade> trades = gson.fromJson(sResponse, tradeCollectionType);
		
		logger.debug("Collection<Trade> object ready.");
		return trades;
	}
}
