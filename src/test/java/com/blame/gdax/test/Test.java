package com.blame.gdax.test;


import com.blame.gdax.api.accounts.AccountsResource;
import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.market.book.Book;
import com.blame.gdax.api.market.book.BookResource;
import com.blame.gdax.api.market.ticker.Ticker;
import com.blame.gdax.api.market.ticker.TickerResource;

public class Test {

	public static void main(String[] args) throws GdaxAPIException {

		String product = "BTC-EUR";
		
		AccountsResource r = new AccountsResource();
		String o = r.getAccounts();
		
		System.out.println(o);
	}

}
