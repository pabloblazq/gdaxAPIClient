package com.blame.gdaxAPIClient;

import java.util.Collection;

import com.blame.gdaxAPIClient.market.book.Book;
import com.blame.gdaxAPIClient.market.book.BookResource;
import com.blame.gdaxAPIClient.market.trades.Trade;
import com.blame.gdaxAPIClient.market.trades.TradesResource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		
		TradesResource r = new TradesResource("BTC-EUR");
		Collection<Trade> trades = r.getTrades();
//		bb.normalize();
//		Thread.sleep(10000);
		Collection<Trade> trades2 = r.getTradesOlder();
		System.out.println(trades2);
	}

}
