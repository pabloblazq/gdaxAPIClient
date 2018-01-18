package com.blame.gdaxAPIClient.signer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
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
	protected Encoder base64Encoder;
	protected Mac sha256HMAC;

	protected Invocation.Builder ib;
	protected String resourcePath;

	public SignableResource(String resourcePath) {
		super();
		
		this.resourcePath = resourcePath;
		
		Properties properties = new Properties();
		InputStream input = null;
		try {
			properties.load(new FileInputStream(PROPERTIES_FILENAME));
			key = properties.getProperty("gdax.api.key");
			secret = properties.getProperty("gdax.api.secret");
			passphrase = properties.getProperty("gdax.api.passphrase");
			
			// initialize the base64 encoder
			this.base64Encoder = Base64.getEncoder();
			
			// initialize the MAC object
			byte[] decodedSecret = Base64.getDecoder().decode(this.secret);
			this.sha256HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(decodedSecret, "HmacSHA256");
			sha256HMAC.init(secretKey);
		} 
		catch (Exception e) {
			logger.error("Unable to initialize the " + SignableResource.class.getSimpleName() + " object: " + e.toString());
			e.printStackTrace();
		} 
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {/* nothing to do */}
			}
		}
	}

	public void signGet() {
		
		ib.header("CB-ACCESS-KEY", this.key);
		
		String timestamp = generateTimestamp();
		ib.header("CB-ACCESS-TIMESTAMP", timestamp);
		
		ib.header("CB-ACCESS-PASSPHRASE", this.passphrase);
		
		try {
			String sign = calculateSign(timestamp, "GET", this.resourcePath);
			ib.header("CB-ACCESS-SIGN", sign);
		} catch (Exception e) {
			logger.error("Unable to initialize the " + SignableResource.class.getSimpleName() + " object: " + e.toString());
			e.printStackTrace();
		}
	}

	private String generateTimestamp() {
		Date dateToSign = new Date();
		return ISO_8601_DATEFORMAT.format(dateToSign);
	}

	protected String calculateSign(String timestamp, String method, String resourcePath) throws IllegalStateException, UnsupportedEncodingException {
		return calculateSign(timestamp, "GET", this.resourcePath, "");
	}

	protected String calculateSign(String timestamp, String method, String resourcePath, String body) throws IllegalStateException, UnsupportedEncodingException {
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
