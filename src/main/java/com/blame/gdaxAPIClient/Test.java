package com.blame.gdaxAPIClient;


import com.blame.gdaxAPIClient.market.book.Book;
import com.blame.gdaxAPIClient.market.book.BookResource;
import com.blame.gdaxAPIClient.market.ticker.Ticker;
import com.blame.gdaxAPIClient.market.ticker.TickerResource;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		String product = "BTC-EUR";
		
		TickerResource r = new TickerResource(product);
		Ticker t = r.getTicker();
		
		System.out.println(t);
	}

}
