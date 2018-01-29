package com.blame.gdax.simul;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.market.trades.Trade;
import com.blame.gdax.api.market.trades.TradesResource;
import com.blame.gdax.api.resource.market.book.BookResource;

public class MarketSimulator {

	protected String product;
	protected TradesResource tradesResource;
	protected BookResource bookResource;

	public MarketSimulator(String product) {
		super();
		this.product = product;
		
		tradesResource = new TradesResource(product);
		bookResource = new BookResource(product, BookResource.DetailLevel.LEVEL_3);
	}
	
	/**
	 * This method works with the product with which it was initialized into
	 * the constructor.
	 * Then it will get the book and tradeList for that product.
	 * It will evaluate the list of trades to know the rate of buy and sell orders received by
	 * the platform.
	 * With that rates it will generate two sets of virtual orders (buy and sell), assigning them
	 * a timestamp when they will be processed.
	 * Then it will simulate the evolution of the market by matching the virtual buy and sell 
	 * "market" orders with the real orders into the Book.
	 * @param numberOfSeconds the virtual number of seconds until when we have to generate virtual orders
	 * @throws GdaxAPIException 
	 */
	public void simulate(int numberOfSeconds) throws GdaxAPIException {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		long currentMillis = cal.getTimeInMillis();
		
		int numberOfSecondsForRates = numberOfSeconds * 10;
		
		ArrayList<Trade> trades = tradesResource.getTrades();
		while((currentMillis - trades.get(trades.size() - 1).getDatetime().getTime()) < (numberOfSecondsForRates * 1000)) {
			trades.addAll(tradesResource.getTradesOlder());
		}
		
		return;
	}
}
