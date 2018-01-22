package com.blame.gdaxAPIClient.market.book;

import java.util.ArrayList;
import java.util.Collection;

public class Book {

	protected String sequence;
	
	protected String[][] bids;
	protected String[][] asks;
	
	protected transient Collection<Order> bidsNormd;
	protected transient Collection<Order> asksNormd;
	
	public void normalize() {
		bidsNormd = new ArrayList<Order>();
		for(String[] bid : bids) {
			Float price = Float.parseFloat(bid[0]);
			Float size = Float.parseFloat(bid[1]);
			int numOrders = Integer.parseInt(bid[2]);
			bidsNormd.add(new Order(price, size, numOrders));
		}
			
		asksNormd = new ArrayList<Order>();
		for(String[] ask : asks) {
			Float price = Float.parseFloat(ask[0]);
			Float size = Float.parseFloat(ask[1]);
			int numOrders = Integer.parseInt(ask[2]);
			asksNormd.add(new Order(price, size, numOrders));
		}
		
	}
	
	
}
