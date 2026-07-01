package com.stryjewski.branchgithubapi.service;

import com.stryjewski.branchgithubapi.client.GithubClient;
import com.stryjewski.branchgithubapi.dto.github.GithubRepoDto;
import com.stryjewski.branchgithubapi.dto.github.GithubUserDto;
import com.stryjewski.branchgithubapi.dto.response.UserProfileResponseDto;
import com.stryjewski.branchgithubapi.mapper.GithubMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GithubUserService {
    private final GithubClient githubClient;
    private final GithubMapper githubMapper;

    public UserProfileResponseDto getUserProfile(String username) {
        GithubUserDto user = githubClient.getUser(username);
        List<GithubRepoDto> repos = githubClient.getRepos(username);

        return githubMapper.mapToUserProfile(user, repos);
    }
}
