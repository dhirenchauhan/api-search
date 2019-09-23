package com.api.apisearch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.apisearch.exception.ResourceNotFoundException;
import com.api.apisearch.util.ConditionCheck;

@Service
public class ApiCallService {

	private RestTemplate restTemplate;
	Logger LOG = LoggerFactory.getLogger(ApiCallService.class);

	public ApiCallService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public <T> T getObjectFor(String url, Class<T> responseType) {
		ConditionCheck.checkRequestElementNotNull(url, "Request URL cannot be null");
		ConditionCheck.checkRequestElementNotNull(responseType, "Response Element cannot be null");
		try {
			return (T) restTemplate.getForObject(url, responseType);
		} catch (Exception ex) {
			LOG.error("External Api request execution failed", ex);
			throw new ResourceNotFoundException("External Api request execution failed", ex);
		}
	}
}
