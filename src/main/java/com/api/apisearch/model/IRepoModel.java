package com.api.apisearch.model;

import java.math.BigInteger;

public interface IRepoModel {

	public BigInteger getId();
	public String getProjectName();
	public String getProjectOwner();
	public String getProjectUrl();
	public String getRepoUrl();
	public RepoType getRepoType();
}
