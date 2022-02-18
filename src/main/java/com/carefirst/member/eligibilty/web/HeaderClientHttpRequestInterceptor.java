package com.carefirst.member.eligibilty.web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class HeaderClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOG = LogManager.getLogger(HeaderClientHttpRequestInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.http.client.ClientHttpRequestInterceptor#intercept(
	 * org.springframework.http.HttpRequest, byte[],
	 * org.springframework.http.client.ClientHttpRequestExecution)
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		LOG.debug("> intercept");
		try {
			String guid = "123213131312";
			LOG.debug("GUID:" + guid);
		
			request.getHeaders().add("guid", guid);
			
			return execution.execute(request, body);
		} finally {
			LOG.debug("< intercept");
		}
	}
	
}