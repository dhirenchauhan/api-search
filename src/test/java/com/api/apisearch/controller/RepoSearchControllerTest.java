package com.api.apisearch.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.api.apisearch.exception.BadRequestException;
import com.api.apisearch.service.RepoSearchService;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RepoSearchControllerTest {
	
	@InjectMocks
	private RepoSearchController repoSearchController;
	
	@Mock
	private RepoSearchService repoSearchService;
	
	@Test(expected = BadRequestException.class)
	public void testUserNull() {
		repoSearchController.getUserRepositories(null, null);
	}
	
	@Test
	public void testProjectOwnerNull() {
		String user = "user";
		repoSearchController.getUserRepositories(user, null);
		verify(repoSearchService, times(1)).getUserByUserId(user);
	}
	
	@Test
	public void testProjectOwnerProvided() {
		String user = "user";
		String projectOwner = "projectOwner";
		repoSearchController.getUserRepositories(user, projectOwner);
		verify(repoSearchService, times(1)).getUserWithProjectOwner(user, projectOwner);
	}
	
}
