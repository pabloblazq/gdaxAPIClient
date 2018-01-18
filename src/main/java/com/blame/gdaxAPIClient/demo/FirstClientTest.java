package com.blame.gdaxAPIClient.demo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class FirstClientTest {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target("http://services.groupkt.com/country");
		Response response = webTarget.path("get").path("all").request(MediaType.APPLICATION_JSON).get();
		//invocationBuilder.header("some-header", "true");
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

}
