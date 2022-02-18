package com.carefirst.member.eligibilty.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import com.carefirst.member.eligibilty.web.ClientHttpRequestFactoryHelper;
import com.carefirst.nexus.gen.idcard.api.MemberIdcardApi;

@Configuration
public class RestTemplateConfig {
	
	private OAuth2ClientContext oauth2ClientContext;
	
	@Value("${nexus.idcard.url}")
	String idCardUrl;
	
	@Bean
	@ConfigurationProperties("application.client.nexus.oauth2")
	protected OAuth2ProtectedResourceDetails nexusOAuth2ProtectedResourceDetails() {
		return new ClientCredentialsResourceDetails();
	}
	
	
	
	
	
	@Bean
	protected RestTemplate nexusRestTemplate() {
		RestTemplate restTemplate = new OAuth2RestTemplate(nexusOAuth2ProtectedResourceDetails());
		HttpComponentsClientHttpRequestFactory nexusClientHttpRequestFactory = ClientHttpRequestFactoryHelper
				.createClientHttpRequestFactory();
		nexusClientHttpRequestFactory.setReadTimeout(300000);
		nexusClientHttpRequestFactory.setConnectionRequestTimeout(20000);
		restTemplate.setRequestFactory(nexusClientHttpRequestFactory);
		return restTemplate;
	}
	
	
	@Bean(name="idCards")
	public MemberIdcardApi memberIdcardApi() {
		com.carefirst.nexus.gen.idcard.invoker.ApiClient apiClient = new com.carefirst.nexus.gen.idcard.invoker.ApiClient(
				nexusRestTemplate());
	
		apiClient.setBasePath(idCardUrl);
		return new MemberIdcardApi(apiClient);
	}
	
}
