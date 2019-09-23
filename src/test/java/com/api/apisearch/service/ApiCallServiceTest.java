package com.api.apisearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.api.apisearch.exception.BadRequestException;
import com.api.apisearch.exception.ResourceNotFoundException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApiCallServiceTest {

	@InjectMocks
	private ApiCallService apiCallService;
	
	@Mock
	private RestTemplate restTemplate;

	@Test
	public void getObjectForTest() {
		apiCallService.getObjectFor("url", Object.class);
		verify(restTemplate,times(1)).getForObject("url", Object.class);
	}
	
	@Test(expected = BadRequestException.class)
	public void getObjectForNullUrl() {
		apiCallService.getObjectFor(null, null);
	}
	
	@Test(expected = BadRequestException.class)
	public void getObjectForNullResponseType() {
		apiCallService.getObjectFor("url", null);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getObjectForException() {
		when(restTemplate.getForObject(anyString(), anyObject())).thenThrow(RuntimeException.class);
		apiCallService.getObjectFor("url", Object.class);
	}
}
