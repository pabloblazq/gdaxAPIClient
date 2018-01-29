package com.blame.gdax.api.resource.sign;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.Invocation;

import com.blame.gdax.api.exception.GdaxAPIException;
import com.blame.gdax.api.resource.Resource;

public abstract class SignableResource extends Resource {
	
	protected static final String PROPERTIES_FILENAME = "gdax_api_key.properties";
//	protected static final SimpleDateFormat ISO_8601_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS000'Z'");
//	static {
//		ISO_8601_DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
//	}
	
	protected String key;
	protected String secret;
	protected String passphrase;
	protected Encoder base64Encoder;
	protected Mac sha256HMAC;

	protected Invocation.Builder invocationBuilder;
	protected String resourcePath;

	public SignableResource(String resourcePath) throws GdaxAPIException {
		super(resourcePath);
		
		this.resourcePath = resourcePath;
		
		logger.info("Initializing objects to perform the signing ...");
		InputStream input = null;
		try {
			logger.debug("Loading GDAX key properties from " + PROPERTIES_FILENAME + " ...");
			//input = new FileInputStream(PROPERTIES_FILENAME);
    		input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
    		Properties properties = new Properties();
			properties.load(input);
			key = properties.getProperty("gdax.api.key");
			secret = properties.getProperty("gdax.api.secret");
			passphrase = properties.getProperty("gdax.api.passphrase");

			logger.debug("Initializing Java Mac object to deal with the key ...");
			// initialize the base64 encoder
			this.base64Encoder = Base64.getEncoder();
			
			// initialize the MAC object
			byte[] decodedSecret = Base64.getDecoder().decode(this.secret);
			this.sha256HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(decodedSecret, "HmacSHA256");
			sha256HMAC.init(secretKey);
			logger.debug("Initialization done.");
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

	public void signGet() throws GdaxAPIException {
		
		invocationBuilder.header("CB-ACCESS-KEY", this.key);
		
		String timestamp = generateTimestamp();
		invocationBuilder.header("CB-ACCESS-TIMESTAMP", timestamp);
		
		invocationBuilder.header("CB-ACCESS-PASSPHRASE", this.passphrase);
		
		try {
			String sign = calculateSign(timestamp, "GET", this.resourcePath);
			invocationBuilder.header("CB-ACCESS-SIGN", sign);
		} catch (Exception e) {
			logger.error("Unable to sign the message because of: " + e.toString());
			e.printStackTrace();
			throw new GdaxAPIException(e);
		}
	}

	private String generateTimestamp() {
		return Long.toString(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime()/1000);
	}

	private String calculateSign(String timestamp, String method, String resourcePath) throws IllegalStateException, UnsupportedEncodingException {
		return calculateSign(timestamp, "GET", this.resourcePath, "");
	}

	private String calculateSign(String timestamp, String method, String resourcePath, String body) throws IllegalStateException, UnsupportedEncodingException {
		//TODO: need to add the header CB-ACCESS-SIGN
		// create the prehash string by concatenating required parts
		// var what = timestamp + method + requestPath + body;

		// decode the base64 secret
		// var key = Buffer(secret, 'base64');

		// create a sha256 hmac with the secret
		// var hmac = crypto.createHmac('sha256', key);

		// sign the require message with the hmac
		// and finally base64 encode the result
		// return hmac.update(what).digest('base64');
		
		String messageToBeSigned = timestamp + "GET" + "/" + resourcePath + body;
		return base64Encoder.encodeToString(sha256HMAC.doFinal(messageToBeSigned.getBytes("UTF-8")));
	}
}
