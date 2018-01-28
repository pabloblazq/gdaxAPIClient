package com.blame.gdax.api.market.trades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.annotations.SerializedName;

public class Trade {
	protected static final SimpleDateFormat ISO_8601_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	static {
		ISO_8601_DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	@SerializedName("time")
	protected String timestamp;
	@SerializedName("trade_id")
	protected String tradeId;
	protected Float price;
	protected Float size;
	protected String side;
	
	protected transient Date datetime;
	
	public String getTimestamp() {
		return timestamp;
	}

	public Date getDatetime() {
		return datetime;
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
	
	public void normalize() throws ParseException {
		datetime = ISO_8601_DATEFORMAT.parse(timestamp);
	}
	
	
}
