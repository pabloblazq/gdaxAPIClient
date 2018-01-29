package com.blame.gdax.api.market.trades;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.resource.Resource;
import com.google.gson.reflect.TypeToken;

public class TradesResource extends Resource {
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_TRADES = "trades";

	protected static Type tradeCollectionType = new TypeToken<ArrayList<Trade>>(){}.getType();

	protected Invocation.Builder invocationBuilder;
	protected String product;
	
	protected String cursorBefore;
	protected String cursorAfter;
	
	public TradesResource(String product) {
		super(new StringBuilder(RESOURCE_PATH_PRODUCTS).append("/").append(product).append("/").append(RESOURCE_PATH_TRADES).toString());

		this.product = product;

		invocationBuilder = getInvocationBuilder();
	}
	
	public ArrayList<Trade> getTrades() throws GdaxAPIException {
		logger.info("Getting last trades ...");
		Response response = invocationBuilder.get();

		logger.debug("Registering pagination cursors ...");
		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");
		
		String sResponse = response.readEntity(String.class);

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		ArrayList<Trade> trades = gson.fromJson(sResponse, tradeCollectionType);
		normalizeTrades(trades);
		
		logger.debug("Collection<Trade> object ready.");
		return trades;
	}
	
	public ArrayList<Trade> getTradesNewer() throws GdaxAPIException {
		logger.info("Getting newer trades ...");
		Invocation.Builder tempIb = getInvocationBuilder("before", cursorBefore);
		Response response = tempIb.get();

		logger.debug("Registering pagination cursors ...");
		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");

		String sResponse = response.readEntity(String.class);

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		ArrayList<Trade> trades = gson.fromJson(sResponse, tradeCollectionType);
		normalizeTrades(trades);
		
		logger.debug("Collection<Trade> object ready.");
		return trades;
	}

	public ArrayList<Trade> getTradesOlder() throws GdaxAPIException {
		logger.info("Getting newer trades ...");
		Invocation.Builder tempIb = getInvocationBuilder("after", cursorAfter);
		Response response = tempIb.get();

		logger.debug("Registering pagination cursors ...");
		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		String sResponse = response.readEntity(String.class);

		logger.debug("Transforming GDAX response into Collection<Trade> object ...");
		ArrayList<Trade> trades = gson.fromJson(sResponse, tradeCollectionType);
		normalizeTrades(trades);
		
		logger.debug("Collection<Trade> object ready.");
		return trades;
	}
	
	private void normalizeTrades(ArrayList<Trade> trades) throws GdaxAPIException {
		try {
			for(Trade trade : trades) {
				trade.normalize();
			}
		} 
		catch (ParseException e) {
			logger.error("Unable to normalize the trade list: " + e.toString());
			e.printStackTrace();
			throw new GdaxAPIException(e);
		}
	}
}
