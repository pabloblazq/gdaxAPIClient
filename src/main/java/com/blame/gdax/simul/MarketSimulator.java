package com.blame.gdax.simul;

import java.util.Collection;

import com.blame.gdax.api.market.book.Book;
import com.blame.gdax.api.market.trades.Trade;

public class MarketSimulator {

	protected Book book;
	protected Collection<Trade> tradeList;

	public MarketSimulator(Book book, Collection<Trade> tradeList) {
		super();
		this.book = book;
		this.tradeList = tradeList;
	}
	
	/**
	 * This method works with the book and tradeList with which it was initialized into
	 * the constructor.
	 * It will evaluate the list of trades to know the rate of buy and sell orders received by
	 * the platform.
	 * With that rates it will generate two sets of virtual orders (buy and sell), assigning them
	 * a timestamp when they will be processed.
	 * Then it will simulate the evolution of the market by matching the virtual buy and sell 
	 * "market" orders with the real orders into the Book.
	 * @param numberOfSeconds the virtual number of seconds until when we have to generate virtual orders
	 */
	public void Simulate(int numberOfSeconds) {
		
		
	}
}
