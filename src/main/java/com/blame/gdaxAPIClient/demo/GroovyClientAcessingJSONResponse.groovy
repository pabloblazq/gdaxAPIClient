package com.blame.gdaxAPIClient.demo

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import groovy.json.JsonSlurper

class GroovyClientAcessingJSONResponse {

	static main(args) {
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target("http://services.groupkt.com/country");
		Response response = webTarget.path("get").path("all").request(MediaType.APPLICATION_JSON).get();
		//invocationBuilder.header("some-header", "true");
		
		// parse the JSON response into an object and return it
		def js = new JsonSlurper()
		def jsonResponse = js.parseText(response.readEntity(String.class))
		
		jsonResponse.RestResponse.result.eachWithIndex { country, i ->
			print i + ": " + country.alpha2_code + ":" + country.name
			println ""
		}
	}

}
