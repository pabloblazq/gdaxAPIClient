package com.blame.gdaxAPIClient.market

import com.blame.gdaxAPIClient.market.book.BookResource

import groovy.json.JsonSlurper

class DemoClient {

	static main(args) {
		def br = new BookResource("BTC-EUR", BookResource.DetailLevel.LEVEL_3)
		def js = new JsonSlurper()
		def jsonResponse = js.parseText(br.get())
		
		print jsonResponse
	}

}
