package com.blame.gdax.api.accounts;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdax.api.GdaxAPIConstants;
import com.blame.gdax.api.exception.GdaxAPIClientException;
import com.blame.gdax.api.sign.SignableResource;

public class AccountsResource extends SignableResource {
	private static final Logger logger = LogManager.getLogger(AccountsResource.class);

	protected static final String RESOURCE_PATH_ACCOUNTS = "accounts";
	
	public AccountsResource() throws GdaxAPIClientException {
		super(RESOURCE_PATH_ACCOUNTS);

		logger.info("Building resource for " + this.getClass().getSimpleName() + " ...");
		ib = ClientBuilder
				.newClient()
				.target(GdaxAPIConstants.GDAX_API_ENDPOINT_URL)
				.path(RESOURCE_PATH_ACCOUNTS)
				.request(MediaType.APPLICATION_JSON);
		
		super.signGet();
	}

	public String getAccounts() {
		logger.info("Sending GET request over the resource...");
		return ib.get().readEntity(String.class);
	}
}
