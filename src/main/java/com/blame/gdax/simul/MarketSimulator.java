package com.blame.gdax.simul;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.resource.market.book.BookResource;
import com.blame.gdax.api.resource.market.trades.Trade;
import com.blame.gdax.api.resource.market.trades.TradesResource;

public class MarketSimulator {

	protected Logger logger;

	protected String product;
	protected String value;
	protected TradesResource tradesResource;
	protected BookResource bookResource;

	public MarketSimulator(String product) {
		super();
		logger = LogManager.getLogger(this.getClass());

		this.product = product;
		value = product.substring(0, product.indexOf("-"));
		
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
		logger.info("Starting simulation for market {}. Time to simulate: {} secs.", product, numberOfSeconds);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		final long currentMillis = cal.getTimeInMillis();
		
		final int numberOfSecondsForRates = numberOfSeconds * 10;
		
		ArrayList<Trade> trades = tradesResource.getTrades();
		while((currentMillis - trades.get(trades.size() - 1).getDatetime().getTime()) < (numberOfSecondsForRates * 1000)) {
			trades.addAll(tradesResource.getTradesOlder());
		}
		trades.removeIf(new Predicate<Trade>() {
		    public boolean test(Trade trade) {
		        if((currentMillis - trade.getDatetime().getTime()) > (numberOfSecondsForRates * 1000)) {
		            return true;
		        }
		        return false;
		    }
		});
		
		// sell - buy
		float buyTotalSize = 0;
		float sellTotalSize = 0;
		for(Trade trade : trades) {
			if(trade.getSide().equals("buy")) {
				buyTotalSize += trade.getSize();
			}
			if(trade.getSide().equals("sell")) {
				sellTotalSize += trade.getSize();
			}
		}
		
		// rates in value/second
		float buyRate = buyTotalSize / numberOfSecondsForRates;
		float sellRate = sellTotalSize / numberOfSecondsForRates;

		logger.info("Calculated rates:");
		logger.info("  Buy rate:  {} {}/sec", buyRate, value);
		logger.info("  Sell rate: {} {}/sec", sellRate, value);
		
		return;
	}
}
