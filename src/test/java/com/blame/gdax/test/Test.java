package com.blame.gdax.test;


import java.util.Collection;

import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.resource.accounts.AccountsResource;
import com.blame.gdax.api.resource.market.book.Book;
import com.blame.gdax.api.resource.market.book.BookResource;
import com.blame.gdax.api.resource.market.ticker.Ticker;
import com.blame.gdax.api.resource.market.ticker.TickerResource;
import com.blame.gdax.api.resource.market.trades.Trade;
import com.blame.gdax.api.resource.market.trades.TradesResource;
import com.blame.gdax.simul.MarketSimulator;

public class Test {

	public static void main(String[] args) throws GdaxAPIException {

		String product = "BTC-EUR";
		
		MarketSimulator ms = new MarketSimulator(product);
		ms.simulate(100);
		
		return;
	}

}
