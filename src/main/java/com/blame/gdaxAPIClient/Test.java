package com.blame.gdaxAPIClient;

import com.blame.gdaxAPIClient.market.book.BookBean;
import com.blame.gdaxAPIClient.market.book.BookResource;
import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) {

		BookResource r = new BookResource("BTC-EUR", BookResource.DetailLevel.LEVEL_2);
		Gson gson = new Gson();
		BookBean bb = gson.fromJson(r.get(), BookBean.class);
		bb.normalize();
		
		System.out.println(bb);
	}

}
