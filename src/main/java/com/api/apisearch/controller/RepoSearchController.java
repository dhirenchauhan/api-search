package com.api.apisearch.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.api.apisearch.model.GitHubRepository;
import com.api.apisearch.model.GitLabRepository;
import com.api.apisearch.model.IRepoModel;
import com.api.apisearch.service.ApiCallService;
import com.api.apisearch.service.RepoSearchService;
import com.api.apisearch.util.ConditionCheck;

@Controller
@RequestMapping(value = "/reposearch")
public class RepoSearchController {

	Logger LOG = LoggerFactory.getLogger(RepoSearchController.class);
	
	private RepoSearchService repoSearchService;
	
	public RepoSearchController(RepoSearchService repoSearchService) {
		this.repoSearchService = repoSearchService;
	}

	@RequestMapping(value="/{user}/projects", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<IRepoModel>> getUserRepositories(@PathVariable("user") String user, 
			@RequestParam(value = "project_owner", required = false) String projectOwner) {

		ConditionCheck.checkRequestElementNotNull(user, "User Cannot be null");
		List<IRepoModel> listRepo = null;
		if( Strings.isNotBlank(projectOwner) ) {
			listRepo = repoSearchService.getUserWithProjectOwner(user, projectOwner);
		} else {
			listRepo = repoSearchService.getUserByUserId(user);
		}
		return ResponseEntity.ok().body(listRepo);
	}
}
