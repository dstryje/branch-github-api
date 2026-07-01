package com.stryjewski.branchgithubapi.dto.github;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GithubRepoDto {
    private String name;

    private String url;
}
