package com.blame.gdax.api.market.book;

import java.util.ArrayList;
import java.util.Collection;

public class Book {

	protected String sequence;
	
	protected String[][] bids;
	protected String[][] asks;
	
	protected transient Collection<BookOrder> bidsNormd;
	protected transient Collection<BookOrder> asksNormd;
	
	public Book normalize(BookResource.DetailLevel level) {
		bidsNormd = new ArrayList<BookOrder>();
		for(String[] bid : bids) {
			Float price = Float.parseFloat(bid[0]);
			Float size = Float.parseFloat(bid[1]);
			int numOrders = 1;
			String orderId = null;
			if(level.equals(BookResource.DetailLevel.LEVEL_3)) {
				orderId =  bid[2];
			}
			else {
				numOrders = Integer.parseInt(bid[2]);
			}
			bidsNormd.add(new BookOrder(price, size, numOrders, orderId));
		}
			
		asksNormd = new ArrayList<BookOrder>();
		for(String[] ask : asks) {
			Float price = Float.parseFloat(ask[0]);
			Float size = Float.parseFloat(ask[1]);
			int numOrders = 1;
			String orderId = null;
			if(level.equals(BookResource.DetailLevel.LEVEL_3)) {
				orderId =  ask[2];
			}
			else {
				numOrders = Integer.parseInt(ask[2]);
			}
			asksNormd.add(new BookOrder(price, size, numOrders, orderId));
		}
		
		return this;
	}
	
}
