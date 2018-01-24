package com.blame.gdax.api.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.sign.SignableResource;

public class AccountsResource extends SignableResource {
	private static final Logger logger = LogManager.getLogger(AccountsResource.class);

	protected static final String RESOURCE_PATH_ACCOUNTS = "accounts";
	
	public AccountsResource() throws GdaxAPIException {
		super(RESOURCE_PATH_ACCOUNTS);

		invocationBuilder = getInvocationBuilder();
	}

	public String getAccounts() throws GdaxAPIException {
		logger.info("Getting accounts ...");
		signGet();
		return invocationBuilder.get().readEntity(String.class);
	}
}
