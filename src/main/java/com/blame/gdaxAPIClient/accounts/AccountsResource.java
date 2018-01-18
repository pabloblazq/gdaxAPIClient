package com.blame.gdaxAPIClient.accounts;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdaxAPIClient.GDAXAPIConstants;
import com.blame.gdaxAPIClient.signer.SignableResource;

public class AccountsResource extends SignableResource {
	private static final Logger logger = LogManager.getLogger(AccountsResource.class);

	protected static final String RESOURCE_PATH_ACCOUNTS = "accounts";
	
	public AccountsResource() {
		super(RESOURCE_PATH_ACCOUNTS);

		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		ib = ClientBuilder
				.newClient()
				.target(GDAXAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_ACCOUNTS)
				.request(MediaType.APPLICATION_JSON);
		
		super.signGet();
	}

	public String get() {
		logger.info("Sending GET request over the resource...");
		return ib.get().readEntity(String.class);
	}
}
