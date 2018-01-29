package com.blame.gdax.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.resource.sign.SignableResource;

public class APIManager {
	private static final Logger logger = LogManager.getLogger(SignableResource.class);
	
	protected static final String PROPERTIES_FILENAME = "gdax_api.properties";

	protected int requestsIntervalMs;

	public APIManager() throws GdaxAPIException {
		super();

		logger.info("Initializing objects to perform the signing ...");

		InputStream input = null;
		try {
			//input = new FileInputStream(PROPERTIES_FILENAME);
    		input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
    		Properties properties = new Properties();
			properties.load(input);
			requestsIntervalMs = Integer.parseInt(properties.getProperty("gdax.api.requestInterval"));
		} 
		catch (Exception e) {
			logger.error("Unable to initialize the " + SignableResource.class.getSimpleName() + " object: " + e.toString());
			e.printStackTrace();
			throw new GdaxAPIException(e);
		} 
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {/* nothing to do */}
			}
		}
	}
	
	
}
