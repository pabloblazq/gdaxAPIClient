package com.blame.gdaxAPIClient

import com.blame.gdaxAPIClient.accounts.AccountsResource
import com.blame.gdaxAPIClient.market.book.BookResource
import com.blame.gdaxAPIClient.time.TimeResource
import groovy.json.JsonSlurper

class GDAXDemoClient {

	static main(args) {
		def r = new BookResource("BTC-EUR", BookResource.DetailLevel.LEVEL_3)
		//def r = new AccountsResource();
		//def r = new TimeResource();
		def js = new JsonSlurper()
		def jsonResponse = js.parseText(r.get())
		
		print jsonResponse
	}

}
