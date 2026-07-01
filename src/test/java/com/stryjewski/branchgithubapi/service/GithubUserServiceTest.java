package com.stryjewski.branchgithubapi.service;

import com.stryjewski.branchgithubapi.client.GithubClient;
import com.stryjewski.branchgithubapi.dto.github.GithubRepoDto;
import com.stryjewski.branchgithubapi.dto.github.GithubUserDto;
import com.stryjewski.branchgithubapi.dto.response.UserProfileResponseDto;
import com.stryjewski.branchgithubapi.mapper.GithubMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GithubUserServiceTest {

    @Mock
    private GithubClient githubClient;

    @Mock
    private GithubMapper githubMapper;

    @InjectMocks
    private GithubUserService githubUserService;

    @Test
    void getUserProfile_shouldReturnMappedResponse() {
        GithubUserDto user = GithubUserDto.builder()
                .login("octocat")
                .build();

        List<GithubRepoDto> repos = List.of(GithubRepoDto.builder()
                .name("repo1")
                .build());

        UserProfileResponseDto expectedResponse = UserProfileResponseDto.builder()
                .userName("octocat")
                .build();

        when(githubClient.getUser("octocat")).thenReturn(user);
        when(githubClient.getRepos("octocat")).thenReturn(repos);
        when(githubMapper.mapToUserProfile(user, repos)).thenReturn(expectedResponse);

        UserProfileResponseDto result = githubUserService.getUserProfile("octocat");

        assertEquals("octocat", result.getUserName());

        verify(githubClient).getUser("octocat");
        verify(githubClient).getRepos("octocat");
        verify(githubMapper).mapToUserProfile(user, repos);
    }
}