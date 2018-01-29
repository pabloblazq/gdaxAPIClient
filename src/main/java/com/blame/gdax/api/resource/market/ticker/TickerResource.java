package com.blame.gdax.api.resource.market.ticker;

import javax.ws.rs.client.Invocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdax.api.resource.Resource;
import com.google.gson.Gson;

/**
 * TODO: REAL-TIME UPDATES : Polling is discouraged in favor of connecting via the websocket stream and listening for match messages.
 * 
 * @author pablo.blazquez
 */
public class TickerResource extends Resource {
	private static final Logger logger = LogManager.getLogger(TickerResource.class);
	
	protected static final String RESOURCE_PATH_PRODUCTS = "products";
	protected static final String RESOURCE_PATH_TICKER = "ticker";
	
	protected Invocation.Builder invocationBuilder;

	protected Gson gson = new Gson();

	public TickerResource(String product) {
		super(new StringBuilder(RESOURCE_PATH_PRODUCTS).append("/").append(product).append("/").append(RESOURCE_PATH_TICKER).toString());

		invocationBuilder = getInvocationBuilder();
	}
	
	public Ticker getTicker() {
		logger.info("Getting ticker ...");
		String sResponse = invocationBuilder.get().readEntity(String.class);
		logger.debug("Transforming GDAX response into Book object ...");
		Ticker ticker = gson.fromJson(sResponse, Ticker.class);
		logger.debug("Ticker object ready.");
		return ticker;
	}
}
