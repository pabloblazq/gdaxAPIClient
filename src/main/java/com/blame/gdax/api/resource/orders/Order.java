package com.blame.gdax.api.resource.orders;

import com.google.gson.annotations.SerializedName;

public class Order {

	/*
    "id": "d0c5340b-6d6c-49d9-b567-48c4bfca13d2",
    "price": "0.10000000",
    "size": "0.01000000",
    "product_id": "BTC-USD",
    "side": "buy",
    "stp": "dc",
    "type": "limit",
    "time_in_force": "GTC",
    "post_only": false,
    "created_at": "2016-12-08T20:02:28.53864Z",
    "fill_fees": "0.0000000000000000",
    "filled_size": "0.00000000",
    "executed_value": "0.0000000000000000",
    "status": "pending",
    "settled": false
    */

	protected String id;
	protected Float price;
	protected Float size;
	@SerializedName("product_id")
	protected String productId;
	protected String side;
	protected String type;
	@SerializedName("time_in_force")
	protected String timeInForce;
	@SerializedName("post_only")
	protected Boolean postOnly;
	@SerializedName("created_at")
	protected String createdAt;
	@SerializedName("fill_fees")
	protected Float fillFees;
	@SerializedName("filled_size")
	protected Float filledSize;
	@SerializedName("executed_value")
	protected Float executedValue;
	protected String status;
	protected String settled;
	public String getId() {
		return id;
	}
	public Float getPrice() {
		return price;
	}
	public Float getSize() {
		return size;
	}
	public String getProductId() {
		return productId;
	}
	public String getSide() {
		return side;
	}
	public String getType() {
		return type;
	}
	public String getTimeInForce() {
		return timeInForce;
	}
	public Boolean getPostOnly() {
		return postOnly;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public Float getFillFees() {
		return fillFees;
	}
	public Float getFilledSize() {
		return filledSize;
	}
	public Float getExecutedValue() {
		return executedValue;
	}
	public String getStatus() {
		return status;
	}
	public String getSettled() {
		return settled;
	}
	

}
