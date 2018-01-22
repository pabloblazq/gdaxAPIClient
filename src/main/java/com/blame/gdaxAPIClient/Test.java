package com.blame.gdaxAPIClient;

import com.blame.gdaxAPIClient.market.book.Book;
import com.blame.gdaxAPIClient.market.book.BookResource;
import com.blame.gdaxAPIClient.market.trades.Trade;
import com.blame.gdaxAPIClient.market.trades.TradesResource;
import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) {

		TradesResource r = new TradesResource("BTC-EUR");
		Gson gson = new Gson();
		String response = r.get();
		Trade[] trades = gson.fromJson(response, Trade[].class);
//		bb.normalize();
		String response2 = r.next();
		Trade[] trades2 = gson.fromJson(response2, Trade[].class);
		System.out.println(trades2);
	}

}
