package com.blame.gdax.api.resource.orders;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import com.blame.gdax.api.resource.Resource;

public class OrdersResource extends Resource {

	protected static final String RESOURCE_PATH_ORDERS = "orders";

	protected Invocation.Builder invocationBuilder;
	protected String product;

	public OrdersResource(String resourcePath, String product) {
		super(new StringBuilder(RESOURCE_PATH_ORDERS).toString());

		this.product = product;
		invocationBuilder = getInvocationBuilder();
	}
	
	public Order placeNewBuyLimitOrder(float size, float price) {
		Form form = new Form();
		form.param("product_id", product);
		form.param("side", "buy");
		form.param("size", Float.toString(size));
		form.param("price", Float.toString(price));
		
		return invocationBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Order.class);
	}
}
