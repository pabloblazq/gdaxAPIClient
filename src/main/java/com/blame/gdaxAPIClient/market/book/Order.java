package com.blame.gdaxAPIClient.market.book;

public class Order {

	protected Float price;
	protected Float size;
	protected int numOrders;
	
	public Order(Float price, Float size, int numOrders) {
		super();
		this.price = price;
		this.size = size;
		this.numOrders = numOrders;
	}

	public Float getPrice() {
		return price;
	}

	public Float getSize() {
		return size;
	}

	public int getNumOrders() {
		return numOrders;
	}

}
