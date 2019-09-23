package com.api.apisearch.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.apisearch.model.GitHubRepository;
import com.api.apisearch.model.GitLabRepository;
import com.api.apisearch.model.IRepoModel;
import com.api.apisearch.util.ConditionCheck;

@Service
public class RepoSearchService {

	private static final String GITLAB_USER_SEARCH_URL = "https://gitlab.com/api/v4/users/"; // dhcoditas/projects
	private static final String GITHUB_USER_SEARCH_URL = "https://api.github.com/users/"; // dhcoditas/repos"
	private static final String PROJECTS_CONSTANT_STR = "/projects";
	private static final String REPOS_CONSTANT_STR = "/repos";

	private ApiCallService apiCallService;

	public RepoSearchService(ApiCallService apiCallService) {
		this.apiCallService = apiCallService;
	}

	public List<IRepoModel> getUserByUserId(String user) {
		ConditionCheck.checkRequestElementNotNull(user, "User Cannot be null");
		return getUsersRepo(user);
	}

	public List<IRepoModel> getUserWithProjectOwner(String user, String projectOwner) {
		ConditionCheck.checkRequestElementNotNull(user, "User Cannot be null");
		ConditionCheck.checkRequestElementNotNull(projectOwner, "Project Owner is null");
		List<IRepoModel> listRepos = getUsersRepo(user);
		if (listRepos != null && !listRepos.isEmpty()) {
			listRepos = listRepos.stream().filter(repo -> projectOwner.equalsIgnoreCase(repo.getProjectOwner()))
					.collect(Collectors.toList());
		}
		return listRepos;
	}

	private List<IRepoModel> getUsersRepo(String user) {
		List<IRepoModel> listRepos = new ArrayList<>();
		listRepos.addAll(Arrays.asList(apiCallService
				.getObjectFor(GITLAB_USER_SEARCH_URL + user + PROJECTS_CONSTANT_STR, GitLabRepository[].class)));
		listRepos.addAll(Arrays.asList(apiCallService.getObjectFor(GITHUB_USER_SEARCH_URL + user + REPOS_CONSTANT_STR,
				GitHubRepository[].class)));
		return listRepos;
	}
}
