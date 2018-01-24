package com.blame.gdax.api.market.trades;

import com.google.gson.annotations.SerializedName;

public class Trade {

	@SerializedName("time")
	protected String timestamp;
	@SerializedName("trade_id")
	protected String tradeId;
	protected Float price;
	protected Float size;
	protected String side;
	
	
	public String getTimestamp() {
		return timestamp;
	}

	public String getTradeId() {
		return tradeId;
	}

	public Float getPrice() {
		return price;
	}
	
	public Float getSize() {
		return size;
	}
	
	public String getSide() {
		return side;
	}
	
	
	
}
