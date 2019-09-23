package com.api.apisearch.model;

import java.math.BigInteger;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepository implements IRepoModel {

	@JsonProperty(value = "id")
	private BigInteger id;

	@JsonProperty(value = "name")
	private String projectName;
	
	private String projectOwner;
	
	@JsonProperty(value = "html_url")
	private String projectUrl;
	
	@JsonProperty(value = "clone_url")
	private String repoUrl;
	
	private RepoType repoType;
	
	public GitHubRepository() {
		this.repoType = RepoType.GITHUB;
	}
	
    @JsonProperty("owner")
    private void unpackNested(Map<String,Object> map) {
		if(map != null) {
			projectOwner = (String) map.get("login");
		}
	}
	
	@Override
	public BigInteger getId() {
		return id;
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

	@Override
	public String getProjectOwner() {
		return projectOwner;
	}

	@Override
	public String getProjectUrl() {
		return projectUrl;
	}

	@Override
	public String getRepoUrl() {
		return repoUrl;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}

	@Override
	public RepoType getRepoType() {
		return repoType;
	}

}
