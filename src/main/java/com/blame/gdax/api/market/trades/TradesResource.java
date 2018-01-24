package com.blame.gdax.api.market.trades;

import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdax.api.GdaxAPIConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TradesResource {
	private static final Logger logger = LogManager.getLogger(TradesResource.class);
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_BOOK = "trades";

	protected static Type tradeCollectionType = new TypeToken<Collection<Trade>>(){}.getType();

	protected Invocation.Builder ib;
	protected WebTarget wt;
	protected String product;
	
	protected String cursorBefore;
	protected String cursorAfter;
	
	protected Gson gson = new Gson();

	public TradesResource(String product) {
		super();
		this.product = product;

		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		wt = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_PRODUCTS).path(product).path(RESOURCE_PATH_BOOK);
		ib = wt.request(MediaType.APPLICATION_JSON);
	}
	
	public Collection<Trade> getTrades() {
		logger.info("Sending GET request over the resource...");

		Response response = this.ib.get();

		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");
		
		String sResponse = response.readEntity(String.class);

		return gson.fromJson(sResponse, tradeCollectionType);
	}
	
	public Collection<Trade> getTradesNewer() {
		Invocation.Builder tempIb = wt
				.queryParam("before", cursorBefore)
				.request(MediaType.APPLICATION_JSON);

		Response response = tempIb.get();

		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");

		String sResponse = response.readEntity(String.class);

		return gson.fromJson(sResponse, tradeCollectionType);
	}

	public Collection<Trade> getTradesOlder() {
		Invocation.Builder tempIb = wt
				.queryParam("after", cursorAfter)
				.request(MediaType.APPLICATION_JSON);

		Response response = tempIb.get();

		this.cursorBefore = response.getHeaderString("CB-BEFORE");
		this.cursorAfter = response.getHeaderString("CB-AFTER");

		String sResponse = response.readEntity(String.class);

		return gson.fromJson(sResponse, tradeCollectionType);
	}
}
