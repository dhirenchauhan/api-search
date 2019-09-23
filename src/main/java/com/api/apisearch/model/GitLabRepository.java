package com.api.apisearch.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitLabRepository implements IRepoModel {
	
	@JsonProperty(value = "id")
	private BigInteger id;

	@JsonProperty(value = "name")
	private String projectName;
	
	private String projectOwner;
	
	@JsonProperty(value = "web_url")
	private String projectUrl;
	
	@JsonProperty(value = "http_url_to_repo")
	private String repoUrl;
	
	private RepoType repoType;
	
	public GitLabRepository() {
		this.repoType = RepoType.GITLAB;
	}
	
    @JsonProperty("namespace")
    private void unpackNested(Map<String,Object> map) {
		if(map != null) {
			projectOwner = (String) map.get("path");
		}
	}
	public BigInteger getId() {
		return id;
	}
	
	@JsonSetter("id")
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	@Override
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Override
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	@Override
	public String getProjectUrl() {
		return projectUrl;
	}
	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}
	@Override
	public String getRepoUrl() {
		return repoUrl;
	}
	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}
	@Override
	public RepoType getRepoType() {
		return repoType;
	}

}
