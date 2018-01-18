package com.blame.gdaxAPIClient.signer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.ws.rs.client.Invocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignableResource {
	private static final Logger logger = LogManager.getLogger(SignableResource.class);
	
	protected static final String PROPERTIES_FILENAME = "gdax_api_key.properties";
	protected static final SimpleDateFormat ISO_8601_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static {
		ISO_8601_DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
			
	protected String key;
	protected String secret;
	protected String passphrase;

	protected Invocation.Builder ib;

	public SignableResource() {
		super();
		Properties properties = new Properties();
		InputStream input = null;
		try {
			properties.load(new FileInputStream(PROPERTIES_FILENAME));
			key = properties.getProperty("gdax.api.key");
			secret = properties.getProperty("gdax.api.secret");
			passphrase = properties.getProperty("gdax.api.passphrase");
		} catch (IOException e) {
			logger.error("Unable to load properties from " + PROPERTIES_FILENAME);
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {/* nothing to do */}
			}
		}
	}

	public void sign() {
		
		ib.header("CB-ACCESS-KEY", this.key);
		
		Date dateToSign = new Date();
		String timestamp = ISO_8601_DATEFORMAT.format(dateToSign);
		ib.header("CB-ACCESS-TIMESTAMP", timestamp);
		
		ib.header("CB-ACCESS-PASSPHRASE", this.passphrase);
		
		//TODO: need to add the header CB-ACCESS-SIGN
	}

}
