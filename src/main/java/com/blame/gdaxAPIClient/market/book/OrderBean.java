package com.blame.gdaxAPIClient.market.book;

public class OrderBean {

	protected Float price;
	protected Float size;
	protected int numOrders;
	
	public OrderBean(Float price, Float size, int numOrders) {
		super();
		this.price = price;
		this.size = size;
		this.numOrders = numOrders;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public int getNumOrders() {
		return numOrders;
	}

	public void setNumOrders(int numOrders) {
		this.numOrders = numOrders;
	}

}
