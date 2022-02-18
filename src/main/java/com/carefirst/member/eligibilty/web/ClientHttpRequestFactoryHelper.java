package com.carefirst.member.eligibilty.web;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class ClientHttpRequestFactoryHelper {

	private static final Logger LOG = LogManager.getLogger(ClientHttpRequestFactoryHelper.class);
	
	private static final int connectionRequestTimeout = 1000;
	
	private static final int connectTimeout = 1000;
	
	private static final int readTimeout = 3000;
	
	public static HttpComponentsClientHttpRequestFactory createClientHttpRequestFactory(Integer maxConnectionsTotal, Integer maxConnectionsPerRoute) {
		LOG.debug("> clientHttpRequestFactory");
		try {
			HttpClientBuilder httpClientBuilder = HttpClients.custom();
			if ( maxConnectionsTotal != null ) {
				httpClientBuilder.setMaxConnTotal(maxConnectionsTotal.intValue());
			}
			if ( maxConnectionsPerRoute != null ) {
				httpClientBuilder.setMaxConnPerRoute(maxConnectionsPerRoute.intValue());
			}
			CloseableHttpClient httpClient = httpClientBuilder.build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			requestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
			requestFactory.setConnectTimeout(connectTimeout);
			requestFactory.setReadTimeout(readTimeout);
			return requestFactory;
		} finally {
			LOG.debug("< clientHttpRequestFactory");
		}
	}
	
	public static HttpComponentsClientHttpRequestFactory createClientHttpRequestFactory() {
		return createClientHttpRequestFactory(null/*maxConnectionsTotal*/,null/*maxConnectionsPerRoute*/);
	}

}
