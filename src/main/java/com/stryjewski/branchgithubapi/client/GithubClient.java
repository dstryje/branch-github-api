package com.stryjewski.branchgithubapi.client;

import com.stryjewski.branchgithubapi.dto.github.GithubRepoDto;
import com.stryjewski.branchgithubapi.dto.github.GithubUserDto;

import java.util.List;

public interface GithubClient {
    GithubUserDto getUser(String username);

    List<GithubRepoDto> getRepos(String username);
}
