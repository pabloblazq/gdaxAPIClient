package com.blame.gdaxAPIClient.market.book;

import java.util.ArrayList;
import java.util.Collection;

public class BookBean {

	protected String sequence;
	
	protected String[][] bids;
	protected String[][] asks;
	
	protected transient Collection<OrderBean> bidsNormd;
	protected transient Collection<OrderBean> asksNormd;
	
	public void normalize() {
		bidsNormd = new ArrayList<OrderBean>();
		for(String[] bid : bids) {
			Float price = Float.parseFloat(bid[0]);
			Float size = Float.parseFloat(bid[1]);
			int numOrders = Integer.parseInt(bid[2]);
			bidsNormd.add(new OrderBean(price, size, numOrders));
		}
			
		asksNormd = new ArrayList<OrderBean>();
		for(String[] ask : asks) {
			Float price = Float.parseFloat(ask[0]);
			Float size = Float.parseFloat(ask[1]);
			int numOrders = Integer.parseInt(ask[2]);
			asksNormd.add(new OrderBean(price, size, numOrders));
		}
		
	}
	
	
}
