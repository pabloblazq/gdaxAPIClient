package com.blame.gdaxAPIClient.market.book;

public class BookOrder {

	protected Float price;
	protected Float size;
	protected String orderId;
	protected int numOrders;
	
	public BookOrder(Float price, Float size, int numOrders, String orderId) {
		super();
		this.price = price;
		this.size = size;
		this.numOrders = numOrders;
		this.orderId = orderId;
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

	public String getOrderId() {
		return orderId;
	}

}
