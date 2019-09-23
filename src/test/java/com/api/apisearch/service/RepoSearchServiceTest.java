package com.api.apisearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.api.apisearch.exception.BadRequestException;
import com.api.apisearch.exception.ResourceNotFoundException;
import com.api.apisearch.model.GitHubRepository;
import com.api.apisearch.model.GitLabRepository;
import com.api.apisearch.model.IRepoModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class RepoSearchServiceTest {

	@InjectMocks
	RepoSearchService repoSearchService;
	
	@Mock
	private ApiCallService apiCallService;
	
	@Test(expected = BadRequestException.class)
	public void getUserByUserIdUserNull() {
		repoSearchService.getUserByUserId(null);
	}
	
	@Test
	public void getUserByUserId( ) {
		GitHubRepository[] repo1 = new GitHubRepository[1];
		repo1[0] = new GitHubRepository();
		GitLabRepository[] repo2 = new GitLabRepository[1];
		repo2[0] = new GitLabRepository();
		when(apiCallService.getObjectFor("https://api.github.com/users/dhcoditas/repos", GitHubRepository[].class)).thenReturn(repo1);
		when(apiCallService.getObjectFor("https://gitlab.com/api/v4/users/dhcoditas/projects", GitLabRepository[].class)).thenReturn(repo2);
		List<IRepoModel> repoList = repoSearchService.getUserByUserId("dhcoditas");
		
		assertNotNull(repoList);
	}
	
	@Test(expected = BadRequestException.class)
	public void getUserWithProjectOwnerUserNull() {
		repoSearchService.getUserWithProjectOwner(null, null);
	}
	
	@Test(expected = BadRequestException.class)
	public void getUserWithProjectOwnerProjectOwnerNull() {
		repoSearchService.getUserWithProjectOwner("user", null);
	}
	
	@Test
	public void getUserWithProjectOwner() {
		GitHubRepository[] repo1 = new GitHubRepository[1];
		repo1[0] = new GitHubRepository();
		repo1[0].setProjectOwner("owner1");
		GitLabRepository[] repo2 = new GitLabRepository[1];
		repo2[0] = new GitLabRepository();
		repo2[0].setProjectOwner("owner2");
		when(apiCallService.getObjectFor("https://api.github.com/users/dhcoditas/repos", GitHubRepository[].class)).thenReturn(repo1);
		when(apiCallService.getObjectFor("https://gitlab.com/api/v4/users/dhcoditas/projects", GitLabRepository[].class)).thenReturn(repo2);
		List<IRepoModel> repoList = repoSearchService.getUserWithProjectOwner("dhcoditas", "owner1");
		assertNotNull(repoList);
		assertEquals(1, repoList.size());
		assertEquals("owner1", repoList.get(0).getProjectOwner());
	}
}
