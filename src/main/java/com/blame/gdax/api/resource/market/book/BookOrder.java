package com.blame.gdax.api.resource.market.book;

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

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setNumOrders(int numOrders) {
		this.numOrders = numOrders;
	}

}
