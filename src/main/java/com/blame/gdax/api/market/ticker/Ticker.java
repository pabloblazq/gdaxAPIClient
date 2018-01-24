package com.blame.gdax.api.market.ticker;

import com.google.gson.annotations.SerializedName;

public class Ticker {

	@SerializedName("trade_id")
	protected String tradeId;
	protected Float price;
	protected Float size;
	protected Float bid;
	protected Float ask;
	protected Float volume;
	@SerializedName("time")
	protected String timestamp;
	
	public String getTradeId() {
		return tradeId;
	}
	public Float getPrice() {
		return price;
	}
	public Float getSize() {
		return size;
	}
	public Float getBid() {
		return bid;
	}
	public Float getAsk() {
		return ask;
	}
	public Float getVolume() {
		return volume;
	}
	public String getTimestamp() {
		return timestamp;
	}
	
}
