package com.blame.gdax.test;


import com.blame.gdax.api.market.book.Book;
import com.blame.gdax.api.market.book.BookResource;
import com.blame.gdax.api.market.ticker.Ticker;
import com.blame.gdax.api.market.ticker.TickerResource;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		String product = "BTC-EUR";
		
		TickerResource r = new TickerResource(product);
		Ticker t = r.getTicker();
		
		System.out.println(t);
	}

}
